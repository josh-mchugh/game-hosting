package com.example.demo.project.aggregate.saga;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.instance.aggregate.command.InstanceGroupCreateCommand;
import com.example.demo.ovh.instance.aggregate.event.InstanceGroupCreatedEvent;
import com.example.demo.ovh.instance.feign.IInstanceGroupFeignService;
import com.example.demo.ovh.instance.feign.model.InstanceGroupApi;
import com.example.demo.project.aggregate.event.ProjectBillingAddedEvent;
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

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class ProjectBuildSagaTest {

    private SagaTestFixture<ProjectBuildSaga> fixture;
    private IInstanceGroupFeignService instanceGroupFeignService;
    private QueryGateway queryGateway;

    @BeforeEach
    public void setup() {

        OvhConfig ovhConfig = new OvhConfig();
        ovhConfig.setProjectId("projectId");

        instanceGroupFeignService = Mockito.mock(IInstanceGroupFeignService.class);
        queryGateway = Mockito.mock(QueryGateway.class);

        fixture = new SagaTestFixture<>(ProjectBuildSaga.class);
        fixture.registerResource(ovhConfig);
        fixture.registerResource(instanceGroupFeignService);
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
                .expectActiveSagas(0);
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

    private InstanceGroupCreateCommand getInstanceGroupCreateCommand() {

        return fixture.getCommandBus().getDispatchedCommands()
                .stream()
                .filter(predicate -> predicate.getCommandName().equals(InstanceGroupCreateCommand.class.getCanonicalName()))
                .map(commandMessage -> (InstanceGroupCreateCommand) commandMessage.getPayload())
                .findFirst()
                .orElse(null);
    }

    private void mockFetchProjectRegionNameBId(UUID id) {

        Mockito.when(queryGateway.query(new FetchProjectRegionNameByIdQuery(id), FetchProjectRegionNameByIdResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchProjectRegionNameByIdResponse("regionName")));
    }
}
