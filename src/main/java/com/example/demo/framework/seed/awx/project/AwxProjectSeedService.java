package com.example.demo.framework.seed.awx.project;

import com.example.demo.awx.feign.ListResponse;
import com.example.demo.awx.notification.aggregate.command.AwxNotificationCreateCommand;
import com.example.demo.awx.notification.feign.NotificationFeignService;
import com.example.demo.awx.notification.feign.model.NotificationApi;
import com.example.demo.awx.notification.feign.model.NotificationConfiguration;
import com.example.demo.awx.notification.feign.model.NotificationCreateApi;
import com.example.demo.awx.project.aggregate.command.AwxProjectCreateCommand;
import com.example.demo.awx.project.feign.ProjectFeignService;
import com.example.demo.awx.project.feign.model.ProjectApi;
import com.example.demo.awx.project.feign.model.ProjectCreateApi;
import com.example.demo.framework.properties.AwxConfig;
import com.example.demo.framework.seed.SeedService;
import com.example.demo.framework.seed.awx.project.projection.model.ExistsAnyAwxProjectQuery;
import com.example.demo.framework.seed.awx.project.projection.model.ExistsAnyAwxProjectResponse;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxCredentialByNameQuery;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxCredentialByNameResponse;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxOrganizationIdByAwxIdQuery;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxOrganizationIdByAwxIdResponse;
import com.example.demo.framework.seed.awx.project.projection.projection.AwxCredentialProjection;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class AwxProjectSeedService implements SeedService<Object> {

    private final AwxConfig awxConfig;
    private final ProjectFeignService projectFeignService;
    private final NotificationFeignService notificationFeignService;
    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    @Override
    public boolean dataNotExists() throws ExecutionException, InterruptedException {

        ExistsAnyAwxProjectQuery query = new ExistsAnyAwxProjectQuery();
        ExistsAnyAwxProjectResponse response = queryGateway.query(query, ExistsAnyAwxProjectResponse.class).get();

        return !response.exists();
    }

    @Override
    public ImmutableList<Object> initializeData() throws ExecutionException, InterruptedException {

        ListResponse<ProjectApi> projectApis = projectFeignService.getProjects();

        Optional<ProjectApi> projectApi = projectApis.getResults().stream()
                .filter(project -> project.getName().equals(awxConfig.getProject().getName()))
                .findFirst();

        if(projectApi.isPresent()) {

            AwxCredentialProjection awxCredentialProjection = getAwxCredentialProjection(awxConfig.getProject().getCredentialName());

            UUID awxOrganizationId = getAwxOrganizationId(projectApi.get().getOrganizationId());

            AwxProjectCreateCommand createCommand = createAwxProjectRequest(awxCredentialProjection.getId(), projectApi.get(), awxOrganizationId);
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

    private UUID createNewAwxProject() throws ExecutionException, InterruptedException {

        AwxCredentialProjection awxCredentialProjection = getAwxCredentialProjection(awxConfig.getProject().getCredentialName());

        // Create Project in AWX
        ProjectApi api = createProjectApi(awxCredentialProjection.getAwxId());

        UUID awxOrganizationId = getAwxOrganizationId(api.getOrganizationId());

        // Persist AwxProject
        AwxProjectCreateCommand projectCreateCommand = createAwxProjectRequest(awxCredentialProjection.getId(), api, awxOrganizationId);
        UUID projectId = commandGateway.sendAndWait(projectCreateCommand);

        // Create Notification for success in AWX
        NotificationCreateApi notificationCreateApi = buildNotificationCreateApi(api);
        NotificationApi notificationApi = notificationFeignService.createSuccessNotificationForProject(api.getId(), notificationCreateApi);

        // Persist AwxNotification
        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxId(notificationApi.getId())
                .awxOrganizationId(awxOrganizationId)
                .name(notificationApi.getName())
                .description(notificationApi.getDescription())
                .type(notificationApi.getType())
                .webhookCallBackUrl(notificationApi.getNotificationConfiguration().getUrl())
                .build();
        commandGateway.send(command);

        return projectId;
    }

    private ProjectApi createProjectApi(Long awxCredentialAwxId) {

        ProjectCreateApi projectCreateApi = ProjectCreateApi.builder()
                .credentialId(awxCredentialAwxId)
                .name(awxConfig.getProject().getName())
                .description(awxConfig.getProject().getDescription())
                .scmType(awxConfig.getProject().getScmType())
                .scmUrl(awxConfig.getProject().getScmUrl())
                .scmBranch(awxConfig.getProject().getScmBranch())
                .build();

        return projectFeignService.createProject(projectCreateApi);
    }

    private AwxCredentialProjection getAwxCredentialProjection(String name) throws ExecutionException, InterruptedException {

        FetchAwxCredentialByNameQuery query = new FetchAwxCredentialByNameQuery(name);
        FetchAwxCredentialByNameResponse response = queryGateway.query(query, FetchAwxCredentialByNameResponse.class).get();

        return response.getProjection();
    }

    private AwxProjectCreateCommand createAwxProjectRequest(String awxCredentialId, ProjectApi projectApi, UUID awxOrganizationId) {

        return AwxProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(awxOrganizationId)
                .awxCredentialId(awxCredentialId)
                .awxId(projectApi.getId())
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

    private UUID getAwxOrganizationId(Long organizationId) throws ExecutionException, InterruptedException {

        FetchAwxOrganizationIdByAwxIdQuery query = new FetchAwxOrganizationIdByAwxIdQuery(organizationId);
        FetchAwxOrganizationIdByAwxIdResponse response = queryGateway.query(query, FetchAwxOrganizationIdByAwxIdResponse.class).get();

        return response.getId();
    }
}
