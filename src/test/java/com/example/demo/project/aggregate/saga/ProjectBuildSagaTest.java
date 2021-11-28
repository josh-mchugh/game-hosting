package com.example.demo.project.aggregate.saga;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.instance.aggregate.command.InstanceCreateCommand;
import com.example.demo.ovh.instance.aggregate.command.InstanceGroupCreateCommand;
import com.example.demo.ovh.instance.aggregate.command.InstanceUpdateCommand;
import com.example.demo.ovh.instance.aggregate.event.InstanceCreatedEvent;
import com.example.demo.ovh.instance.aggregate.event.InstanceGroupCreatedEvent;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.ovh.instance.feign.InstanceFeignService;
import com.example.demo.ovh.instance.feign.InstanceGroupFeignService;
import com.example.demo.ovh.instance.feign.model.InstanceApi;
import com.example.demo.ovh.instance.feign.model.InstanceCreateApi;
import com.example.demo.ovh.instance.feign.model.InstanceGroupApi;
import com.example.demo.project.aggregate.command.ProjectStateCreateInstanceUpdateCommand;
import com.example.demo.project.aggregate.event.ProjectBillingAddedEvent;
import com.example.demo.project.aggregate.event.ProjectStateCreateInstanceUpdatedEvent;
import com.example.demo.project.aggregate.saga.projection.model.FetchAnsibleCredentialQuery;
import com.example.demo.project.aggregate.saga.projection.model.FetchAnsibleCredentialResponse;
import com.example.demo.project.aggregate.saga.projection.model.FetchProjectCreateInstanceDetailsQuery;
import com.example.demo.project.aggregate.saga.projection.model.FetchProjectCreateInstanceDetailsResponse;
import com.example.demo.project.aggregate.saga.projection.model.FetchProjectRegionNameByIdQuery;
import com.example.demo.project.aggregate.saga.projection.model.FetchProjectRegionNameByIdResponse;
import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.test.matchers.Matchers;
import org.axonframework.test.saga.SagaTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class ProjectBuildSagaTest {

    private SagaTestFixture<ProjectBuildSaga> fixture;
    private InstanceGroupFeignService instanceGroupFeignService;
    private InstanceFeignService instanceFeignService;
    private QueryGateway queryGateway;

    @BeforeEach
    public void setup() {

        OvhConfig ovhConfig = new OvhConfig();
        ovhConfig.setProjectId("projectId");

        instanceGroupFeignService = Mockito.mock(InstanceGroupFeignService.class);
        instanceFeignService = Mockito.mock(InstanceFeignService.class);
        queryGateway = Mockito.mock(QueryGateway.class);

        fixture = new SagaTestFixture<>(ProjectBuildSaga.class);
        fixture.registerResource(ovhConfig);
        fixture.registerResource(instanceGroupFeignService);
        fixture.registerResource(instanceFeignService);
        fixture.registerResource(queryGateway);
    }

    @Test
    public void whenProjectBillingAddedEvent() {

        UUID projectId = UUID.randomUUID();
        ProjectBillingAddedEvent event = projectBillingAddedEvent(projectId);

        mockFetchProjectRegionNameBId(projectId);
        mockInstanceGroupCreateApi();

        fixture.givenAggregate(projectId.toString())
                .published()
                .whenAggregate(projectId.toString())
                .publishes(event)
                .expectActiveSagas(1)
                .expectDispatchedCommandsMatching(
                        Matchers.exactSequenceOf(
                                Matchers.predicate(payload -> payload.getCommandName().equals(InstanceGroupCreateCommand.class.getCanonicalName()))
                        )
                );
    }

    @Test
    public void whenInstanceGroupCreated() {

        UUID projectId = UUID.randomUUID();
        ProjectBillingAddedEvent projectCreatedEvent = projectBillingAddedEvent(projectId);

        mockFetchProjectRegionNameBId(projectId);
        mockInstanceGroupCreateApi();

        fixture.givenAggregate(projectId.toString())
                .published(projectCreatedEvent);

        InstanceGroupCreateCommand command = getInstanceGroupCreateCommand();
        InstanceGroupCreatedEvent instanceGroupCreatedEvent = instanceGroupCreatedEvent(command.getId(), projectId);

        fixture.whenAggregate(projectId.toString())
                .publishes(instanceGroupCreatedEvent)
                .expectActiveSagas(1)
                .expectDispatchedCommandsMatching(
                        Matchers.exactSequenceOf(
                                Matchers.predicate(payload -> payload.getCommandName().equals(ProjectStateCreateInstanceUpdateCommand.class.getCanonicalName()))
                        )
                );
    }

    @Test
    public void whenProjectStateCreateInstanceUpdated() {

        UUID projectId = UUID.randomUUID();
        ProjectBillingAddedEvent projectCreatedEvent = projectBillingAddedEvent(projectId);

        mockFetchProjectRegionNameBId(projectId);
        mockInstanceGroupCreateApi();
        mockFetchAnsibleCredentials();
        mockCreateInstance();
        mockFetchProjectCreateInstanceDetails(projectId);

        fixture.givenAggregate(projectId.toString())
                .published(projectCreatedEvent);

        InstanceGroupCreateCommand instanceGroupCreateCommand = getInstanceGroupCreateCommand();
        InstanceGroupCreatedEvent instanceGroupCreatedEvent = instanceGroupCreatedEvent(instanceGroupCreateCommand.getId(), projectId);

        fixture.givenAggregate(projectId.toString())
                .published(instanceGroupCreatedEvent);

        ProjectStateCreateInstanceUpdatedEvent event = ProjectStateCreateInstanceUpdatedEvent.builder()
                .id(projectId)
                .status(ProjectStatus.BUILD)
                .state(ProjectState.BUILD_CREATE_INSTANCE)
                .build();

        fixture.whenAggregate(projectId.toString())
                .publishes(event)
                .expectActiveSagas(1)
                .expectDispatchedCommandsMatching(
                        Matchers.exactSequenceOf(
                                Matchers.predicate(payload -> payload.getCommandName().equals(InstanceCreateCommand.class.getCanonicalName()))
                        )
                );
    }

    @Test
    public void whenInstanceCreatedEvent() {

        UUID projectId = UUID.randomUUID();
        ProjectBillingAddedEvent projectCreatedEvent = projectBillingAddedEvent(projectId);

        mockFetchProjectRegionNameBId(projectId);
        mockInstanceGroupCreateApi();
        mockFetchAnsibleCredentials();
        mockCreateInstance();
        mockFetchProjectCreateInstanceDetails(projectId);

        fixture.givenAggregate(projectId.toString())
                .published(projectCreatedEvent);

        UUID instanceGroupId = getInstanceGroupCreateCommand().getId();
        InstanceGroupCreatedEvent instanceGroupCreatedEvent = instanceGroupCreatedEvent(instanceGroupId, projectId);

        fixture.givenAggregate(projectId.toString())
                .published(instanceGroupCreatedEvent);

        ProjectStateCreateInstanceUpdatedEvent projectStateCreateInstanceUpdatedEvent = ProjectStateCreateInstanceUpdatedEvent.builder()
                .id(projectId)
                .status(ProjectStatus.BUILD)
                .state(ProjectState.BUILD_CREATE_INSTANCE)
                .build();

        fixture.givenAggregate(instanceGroupId.toString())
                .published(projectStateCreateInstanceUpdatedEvent);

        UUID instanceId = getInstanceCreateCommand().getId();
        InstanceCreatedEvent event = instanceCreatedEvent(instanceId, instanceGroupId, projectId);
        mockGetInstanceById(instanceId);

        fixture.whenAggregate(instanceId.toString())
                .publishes(event)
                .expectActiveSagas(0)
                .expectDispatchedCommandsMatching(
                        Matchers.exactSequenceOf(
                                Matchers.predicate(payload -> payload.getCommandName().equals(InstanceUpdateCommand.class.getCanonicalName()))
                        )
                );
    }

    private ProjectBillingAddedEvent projectBillingAddedEvent(UUID id) {

        return ProjectBillingAddedEvent.builder()
                .id(id)
                .status(ProjectStatus.BUILD)
                .state(ProjectState.BUILD_CREATE_INSTANCE_GROUP)
                .build();
    }

    private void mockInstanceGroupCreateApi() {

        InstanceGroupApi instanceGroupApi = new InstanceGroupApi();
        instanceGroupApi.setId("id");
        instanceGroupApi.setName("name");
        instanceGroupApi.setType("type");

        Mockito.when(instanceGroupFeignService.createInstanceGroup(Mockito.any())).thenReturn(instanceGroupApi);
    }

    private InstanceGroupCreatedEvent instanceGroupCreatedEvent(UUID id, UUID projectId) {

        return InstanceGroupCreatedEvent.builder()
                .id(id)
                .name("name")
                .projectId(projectId)
                .type("type")
                .ovhId("ovhId")
                .build();
    }

    private InstanceCreatedEvent instanceCreatedEvent(UUID id, UUID instanceGroupId, UUID projectId) {

        return InstanceCreatedEvent.builder()
                .id(id)
                .ovhId("ovhId")
                .credentialId(UUID.randomUUID())
                .instanceCreatedDate(LocalDateTime.now())
                .flavorId(UUID.randomUUID())
                .imageId(UUID.randomUUID())
                .instanceGroupId(instanceGroupId)
                .name(projectId.toString())
                .status(InstanceStatus.BUILD)
                .build();
    }

    private InstanceGroupCreateCommand getInstanceGroupCreateCommand() {

        return fixture.getCommandBus().getDispatchedCommands()
                .stream()
                .filter(predicate -> predicate.getCommandName().equals(InstanceGroupCreateCommand.class.getCanonicalName()))
                .map(commandMessage -> (InstanceGroupCreateCommand) commandMessage.getPayload())
                .findFirst()
                .orElse(null);
    }

    private InstanceCreateCommand getInstanceCreateCommand() {

        return fixture.getCommandBus().getDispatchedCommands()
                .stream()
                .filter(predicate -> predicate.getCommandName().equals(InstanceCreateCommand.class.getCanonicalName()))
                .map(commandMessage -> (InstanceCreateCommand) commandMessage.getPayload())
                .findFirst()
                .orElse(null);
    }

    private void mockFetchProjectRegionNameBId(UUID id) {

        Mockito.when(queryGateway.query(new FetchProjectRegionNameByIdQuery(id), FetchProjectRegionNameByIdResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchProjectRegionNameByIdResponse("regionName")));
    }

    private void mockFetchAnsibleCredentials() {

        Mockito.when(queryGateway.query(new FetchAnsibleCredentialQuery(), FetchAnsibleCredentialResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchAnsibleCredentialResponse(UUID.randomUUID(), "ovhId")));
    }

    private void mockFetchProjectCreateInstanceDetails(UUID id) {

        FetchProjectCreateInstanceDetailsResponse response = FetchProjectCreateInstanceDetailsResponse.builder()
                .regionName("regionName")
                .flavorId(UUID.randomUUID())
                .flavorOvhId("flavorOvhId")
                .imageId(UUID.randomUUID())
                .imageOvhId("imageOvhId")
                .instanceGroupId(UUID.randomUUID())
                .instanceGroupOvhId("instanceGroupOvhId")
                .build();

        Mockito.when(queryGateway.query(new FetchProjectCreateInstanceDetailsQuery(id), FetchProjectCreateInstanceDetailsResponse.class))
                .thenReturn(CompletableFuture.completedFuture(response));
    }

    private void mockCreateInstance() {

        InstanceApi instanceApi = new InstanceApi();
        instanceApi.setId("id");
        instanceApi.setName("name");
        instanceApi.setStatus(InstanceStatus.BUILD);
        instanceApi.setCreatedDate(LocalDateTime.now());

        Mockito.when(instanceFeignService.createInstance(Mockito.any(InstanceCreateApi.class))).thenReturn(instanceApi);
    }

    private void mockGetInstanceById(UUID instanceId) {

        InstanceApi instanceApiBuild = new InstanceApi();
        instanceApiBuild.setId(instanceId.toString());
        instanceApiBuild.setStatus(InstanceStatus.BUILD);

        InstanceApi instanceApiActive = new InstanceApi();
        instanceApiActive.setId(instanceId.toString());
        instanceApiActive.setStatus(InstanceStatus.ACTIVE);

        Mockito.when(instanceFeignService.getInstanceById(Mockito.anyString()))
                .thenReturn(instanceApiBuild)
                .thenReturn(instanceApiActive);
    }
}
