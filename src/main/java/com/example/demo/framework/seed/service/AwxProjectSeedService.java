package com.example.demo.framework.seed.service;

import com.example.demo.awx.credential.entity.model.AwxCredential;
import com.example.demo.awx.credential.entity.service.IAwxCredentialService;
import com.example.demo.awx.credential.projection.IAwxCredentialProjector;
import com.example.demo.awx.feign.common.ListResponse;
import com.example.demo.awx.feign.notification.NotificationClient;
import com.example.demo.awx.feign.notification.model.NotificationApi;
import com.example.demo.awx.feign.notification.model.NotificationConfiguration;
import com.example.demo.awx.feign.notification.model.NotificationCreateApi;
import com.example.demo.awx.feign.project.ProjectClient;
import com.example.demo.awx.feign.project.model.ProjectApi;
import com.example.demo.awx.feign.project.model.ProjectCreateApi;
import com.example.demo.awx.notification.aggregate.command.AwxNotificationCreateCommand;
import com.example.demo.awx.project.model.AwxProject;
import com.example.demo.awx.project.service.IAwxProjectService;
import com.example.demo.awx.project.service.model.AwxProjectCreateRequest;
import com.example.demo.framework.properties.AwxConfig;
import com.example.demo.framework.seed.ISeedService;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AwxProjectSeedService implements ISeedService<AwxProject> {

    private final AwxConfig awxConfig;
    private final IAwxCredentialProjector awxCredentialProjector;
    private final IAwxProjectService awxProjectService;
    private final ProjectClient projectClient;
    private final NotificationClient notificationClient;
    private final CommandGateway commandGateway;

    @Override
    public boolean dataNotExists() {

        return !awxProjectService.existsAny();
    }

    @Override
    public ImmutableList<AwxProject> initializeData() {

        ListResponse<ProjectApi> projectApis = projectClient.getProjects(awxConfig.getOrganization().getId());

        Optional<ProjectApi> projectApi = projectApis.getResults().stream()
                .filter(project -> project.getName().equals(awxConfig.getProject().getName()))
                .findFirst();

        if(projectApi.isPresent()) {

            AwxProjectCreateRequest request = createAwxProjectRequest(projectApi.get());
            AwxProject awxProject = awxProjectService.handleCreateRequest(request);

            return ImmutableList.of(awxProject);
        }

        return ImmutableList.of(createNewAwxProject());
    }

    @Override
    public String type() {

        return "Awx Project";
    }

    @Override
    public Integer order() {

        return 9;
    }

    private AwxProject createNewAwxProject() {

        // Create Project in AWX
        ProjectApi api = createProjectApi();

        // Persist AwxProject
        AwxProjectCreateRequest request = createAwxProjectRequest(api);
        AwxProject awxProject = awxProjectService.handleCreateRequest(request);

        // Create Notification for success in AWX
        NotificationCreateApi notificationCreateApi = buildNotificationCreateApi(awxProject);
        NotificationApi notificationApi = notificationClient.createSuccessNotificationForProject(awxProject.getProjectId(), notificationCreateApi);

        // Persist AwxNotification
        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .id(UUID.randomUUID())
                .notificationId(notificationApi.getId())
                .organizationId(notificationApi.getOrganizationId())
                .name(notificationApi.getName())
                .description(notificationApi.getDescription())
                .notificationType(notificationApi.getNotificationType())
                .webhookCallBackUrl(notificationApi.getNotificationConfiguration().getUrl())
                .build();
        commandGateway.send(command);

        return awxProject;
    }

    private ProjectApi createProjectApi() {

        AwxCredential awxCredential = awxCredentialProjector.getByName(awxConfig.getProject().getCredentialName());

        ProjectCreateApi projectCreateApi = ProjectCreateApi.builder()
                .credentialId(awxCredential.getCredentialId())
                .name(awxConfig.getProject().getName())
                .description(awxConfig.getProject().getDescription())
                .scmType(awxConfig.getProject().getScmType())
                .scmUrl(awxConfig.getProject().getScmUrl())
                .scmBranch(awxConfig.getProject().getScmBranch())
                .build();

        return projectClient.createProject(awxConfig.getOrganization().getId(), projectCreateApi);
    }

    private AwxProjectCreateRequest createAwxProjectRequest(ProjectApi projectApi) {

        return AwxProjectCreateRequest.builder()
                .organizationId(projectApi.getOrganizationId())
                .credentialId(projectApi.getCredentialId())
                .projectId(projectApi.getId())
                .name(projectApi.getName())
                .description(projectApi.getDescription())
                .scmType(projectApi.getScmType())
                .scmBranch(projectApi.getScmBranch())
                .scmUrl(projectApi.getScmUrl())
                .build();
    }

    private NotificationCreateApi buildNotificationCreateApi(AwxProject awxProject) {

        String url = UriComponentsBuilder.fromHttpUrl(awxConfig.getNotificationBaseUrl())
                .path(String.format("/awx/notification/project/%s/success", awxProject.getProjectId()))
                .toUriString();

        return NotificationCreateApi.builder()
                .name(String.format("Project - %s - %s", awxProject.getName(), awxProject.getProjectId()))
                .notificationConfiguration(new NotificationConfiguration(url))
                .notificationType("webhook")
                .organizationId(awxConfig.getOrganization().getId())
                .build();
    }
}
