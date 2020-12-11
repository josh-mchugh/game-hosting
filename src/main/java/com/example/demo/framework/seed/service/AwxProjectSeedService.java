package com.example.demo.framework.seed.service;

import com.example.demo.awx.credential.entity.model.AwxCredential;
import com.example.demo.awx.credential.projection.IAwxCredentialProjector;
import com.example.demo.awx.feign.ListResponse;
import com.example.demo.awx.notification.aggregate.command.AwxNotificationCreateCommand;
import com.example.demo.awx.notification.feign.INotificationFeignService;
import com.example.demo.awx.notification.feign.model.NotificationApi;
import com.example.demo.awx.notification.feign.model.NotificationConfiguration;
import com.example.demo.awx.notification.feign.model.NotificationCreateApi;
import com.example.demo.awx.organization.projection.IAwxOrganizationProjection;
import com.example.demo.awx.organization.projection.model.FetchAwxOrganizationIdByAwxIdQuery;
import com.example.demo.awx.organization.projection.model.FetchAwxOrganizationIdByAwxIdResponse;
import com.example.demo.awx.project.aggregate.command.AwxProjectCreateCommand;
import com.example.demo.awx.project.feign.ProjectClient;
import com.example.demo.awx.project.feign.model.ProjectApi;
import com.example.demo.awx.project.feign.model.ProjectCreateApi;
import com.example.demo.awx.project.projection.IAwxProjectProjector;
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
public class AwxProjectSeedService implements ISeedService<Object> {

    private final AwxConfig awxConfig;
    private final ProjectClient projectClient;
    private final INotificationFeignService notificationFeignService;
    private final IAwxCredentialProjector awxCredentialProjector;
    private final IAwxProjectProjector awxProjectProjector;
    private final IAwxOrganizationProjection awxOrganizationProjection;
    private final CommandGateway commandGateway;

    @Override
    public boolean dataNotExists() {

        return !awxProjectProjector.existsAny();
    }

    @Override
    public ImmutableList<Object> initializeData() {

        ListResponse<ProjectApi> projectApis = projectClient.getProjects(awxConfig.getOrganization().getId());

        Optional<ProjectApi> projectApi = projectApis.getResults().stream()
                .filter(project -> project.getName().equals(awxConfig.getProject().getName()))
                .findFirst();

        if(projectApi.isPresent()) {

            AwxCredential awxCredential = awxCredentialProjector.getByName(awxConfig.getProject().getCredentialName());

            AwxProjectCreateCommand createCommand = createAwxProjectRequest(awxCredential, projectApi.get());
            UUID awxProjectId = commandGateway.sendAndWait(createCommand);

            return ImmutableList.of(awxProjectId);
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

    private UUID createNewAwxProject() {

        AwxCredential awxCredential = awxCredentialProjector.getByName(awxConfig.getProject().getCredentialName());

        // Create Project in AWX
        ProjectApi api = createProjectApi(awxCredential);

        // Persist AwxProject
        AwxProjectCreateCommand projectCreateCommand = createAwxProjectRequest(awxCredential, api);
        UUID projectId = commandGateway.sendAndWait(projectCreateCommand);

        // Create Notification for success in AWX
        NotificationCreateApi notificationCreateApi = buildNotificationCreateApi(api);
        NotificationApi notificationApi = notificationFeignService.createSuccessNotificationForProject(api.getId(), notificationCreateApi);

        FetchAwxOrganizationIdByAwxIdQuery query = new FetchAwxOrganizationIdByAwxIdQuery(notificationApi.getOrganizationId());
        FetchAwxOrganizationIdByAwxIdResponse response = awxOrganizationProjection.fetchAwxOrganizationIdByAwxId(query);

        // Persist AwxNotification
        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxId(notificationApi.getId())
                .awxOrganizationId(response.getId())
                .name(notificationApi.getName())
                .description(notificationApi.getDescription())
                .type(notificationApi.getType())
                .webhookCallBackUrl(notificationApi.getNotificationConfiguration().getUrl())
                .build();
        commandGateway.send(command);

        return projectId;
    }

    private ProjectApi createProjectApi(AwxCredential awxCredential) {

        ProjectCreateApi projectCreateApi = ProjectCreateApi.builder()
                .credentialId(awxCredential.getAwxId())
                .name(awxConfig.getProject().getName())
                .description(awxConfig.getProject().getDescription())
                .scmType(awxConfig.getProject().getScmType())
                .scmUrl(awxConfig.getProject().getScmUrl())
                .scmBranch(awxConfig.getProject().getScmBranch())
                .build();

        return projectClient.createProject(awxConfig.getOrganization().getId(), projectCreateApi);
    }

    private AwxProjectCreateCommand createAwxProjectRequest(AwxCredential awxCredential, ProjectApi projectApi) {

        return AwxProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(projectApi.getOrganizationId())
                .awxCredentialId(awxCredential.getId())
                .projectId(projectApi.getId())
                .name(projectApi.getName())
                .description(projectApi.getDescription())
                .scmType(projectApi.getScmType())
                .scmBranch(projectApi.getScmBranch())
                .scmUrl(projectApi.getScmUrl())
                .build();

    }

    private NotificationCreateApi buildNotificationCreateApi(ProjectApi projectApi) {

        String url = UriComponentsBuilder.fromHttpUrl(awxConfig.getNotificationBaseUrl())
                .path(String.format("/awx/notification/project/%s/success", projectApi.getId()))
                .toUriString();

        NotificationConfiguration configuration = NotificationConfiguration.builder()
                .url(url)
                .build();

        return NotificationCreateApi.builder()
                .name(String.format("Project - %s - %s", projectApi.getName(), projectApi.getId()))
                .notificationConfiguration(configuration)
                .type("webhook")
                .organizationId(awxConfig.getOrganization().getId())
                .build();
    }
}
