package com.example.demo.project.aggregate.saga;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.instance.aggregate.command.InstanceCreateCommand;
import com.example.demo.ovh.instance.aggregate.command.InstanceGroupCreateCommand;
import com.example.demo.ovh.instance.aggregate.command.InstanceUpdateCommand;
import com.example.demo.ovh.instance.aggregate.event.InstanceCreatedEvent;
import com.example.demo.ovh.instance.aggregate.event.InstanceGroupCreatedEvent;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.ovh.instance.feign.IInstanceFeignService;
import com.example.demo.ovh.instance.feign.IInstanceGroupFeignService;
import com.example.demo.ovh.instance.feign.model.InstanceApi;
import com.example.demo.ovh.instance.feign.model.InstanceGroupApi;
import com.example.demo.ovh.instance.feign.model.IpAddressApi;
import com.example.demo.project.aggregate.event.ProjectCreatedEvent;
import com.example.demo.project.aggregate.saga.model.FetchAnsibleCredentialQuery;
import com.example.demo.project.aggregate.saga.model.FetchAnsibleCredentialResponse;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.test.matchers.Matchers;
import org.axonframework.test.saga.SagaTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class ProjectCreatedSagaTest {

    private SagaTestFixture<ProjectCreatedSaga> fixture;
    private IInstanceGroupFeignService instanceGroupFeignService;
    private IInstanceFeignService instanceFeignService;
    private QueryGateway queryGateway;

    @BeforeEach
    public void setup() {

        OvhConfig ovhConfig = new OvhConfig();
        ovhConfig.setProjectId("projectId");

        instanceGroupFeignService = Mockito.mock(IInstanceGroupFeignService.class);
        instanceFeignService = Mockito.mock(IInstanceFeignService.class);
        queryGateway = Mockito.mock(QueryGateway.class);

        fixture = new SagaTestFixture<>(ProjectCreatedSaga.class);
        fixture.registerResource(ovhConfig);
        fixture.registerResource(instanceGroupFeignService);
        fixture.registerResource(queryGateway);
        fixture.registerResource(instanceFeignService);
    }

    @Test
    public void whenProjectCreated() {

        mockInstanceGroupCreateApi();

        UUID projectId = UUID.randomUUID();
        ProjectCreatedEvent event = projectCreatedEvent(projectId);

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

        mockInstanceGroupCreateApi();
        mockInstanceCreateApi();

        UUID projectId = UUID.randomUUID();
        ProjectCreatedEvent projectCreatedEvent = projectCreatedEvent(projectId);

        fixture.givenAggregate(projectId.toString())
                .published(projectCreatedEvent);

        InstanceGroupCreateCommand command = getInstanceGroupCreateCommand();
        InstanceGroupCreatedEvent instanceGroupCreatedEvent = instanceGroupCreatedEvent(command.getId(), projectId);

        fixture.whenAggregate(projectId.toString())
                .publishes(instanceGroupCreatedEvent)
                .expectActiveSagas(1)
                .expectDispatchedCommandsMatching(
                        Matchers.exactSequenceOf(
                                Matchers.predicate(payload -> payload.getCommandName().equals(InstanceCreateCommand.class.getCanonicalName()))
                        )
                );
    }

    @Test
    public void whenInstanceCreated() {

        mockInstanceGroupCreateApi();
        mockInstanceCreateApi();
        mockGetInstanceById();

        UUID projectId = UUID.randomUUID();
        ProjectCreatedEvent projectCreatedEvent = projectCreatedEvent(projectId);

        fixture.givenAggregate(projectId.toString())
                .published(projectCreatedEvent);

        InstanceGroupCreateCommand instanceGroupCreateCommand = getInstanceGroupCreateCommand();
        InstanceGroupCreatedEvent instanceGroupCreatedEvent = instanceGroupCreatedEvent(instanceGroupCreateCommand.getId(), projectId);

        fixture.andThenAggregate(projectId.toString())
                .published(instanceGroupCreatedEvent);

        InstanceCreateCommand instanceCreateCommand = getInstanceCreateCommand();
        InstanceCreatedEvent instanceCreatedEvent = instanceCreatedEvent(instanceCreateCommand.getId());

        fixture.whenAggregate(instanceCreateCommand.getId().toString())
                .publishes(instanceCreatedEvent)
                .expectActiveSagas(0)
                .expectDispatchedCommandsMatching(
                        Matchers.exactSequenceOf(
                                Matchers.predicate(payload -> payload.getCommandName().equals(InstanceUpdateCommand.class.getCanonicalName()))
                        )
                );
    }

    private ProjectCreatedEvent projectCreatedEvent(UUID id) {

        return ProjectCreatedEvent.builder()
                .id(id)
                .name("name")
                .gameId(UUID.randomUUID())
                .member(ProjectCreatedEvent.createMember(UUID.randomUUID()))
                .build();
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

    private InstanceCreatedEvent instanceCreatedEvent(UUID id) {

        return InstanceCreatedEvent.builder()
                .id(id)
                .ovhId("ovhId")
                .build();
    }

    private void mockInstanceGroupCreateApi() {

        InstanceGroupApi instanceGroupApi = new InstanceGroupApi();
        instanceGroupApi.setId("id");
        instanceGroupApi.setName("name");
        instanceGroupApi.setType("type");

        Mockito.when(instanceGroupFeignService.createInstanceGroup(Mockito.any())).thenReturn(instanceGroupApi);
    }

    private void mockInstanceCreateApi() {

        Mockito.when(queryGateway.query(new FetchAnsibleCredentialQuery(), FetchAnsibleCredentialResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchAnsibleCredentialResponse("credentialId")));

        InstanceApi instanceApi = new InstanceApi();
        instanceApi.setId("id");
        instanceApi.setName("name");
        instanceApi.setStatus(InstanceStatus.BUILD);
        instanceApi.setCreatedDate(LocalDateTime.now());

        Mockito.when(instanceFeignService.createInstance(Mockito.any())).thenReturn(instanceApi);
    }

    private void mockGetInstanceById() {

        InstanceApi buildingResponse = new InstanceApi();
        buildingResponse.setStatus(InstanceStatus.BUILD);

        IpAddressApi ip4Address = new IpAddressApi();
        ip4Address.setIp("ip4Address");
        ip4Address.setVersion(4);

        IpAddressApi ip6Address = new IpAddressApi();
        ip6Address.setIp("ip6Address");
        ip6Address.setVersion(6);

        InstanceApi activeResponse = new InstanceApi();
        activeResponse.setId("instanceId");
        activeResponse.setName("name");
        activeResponse.setStatus(InstanceStatus.ACTIVE);
        activeResponse.setIpAddresses(Arrays.asList(ip4Address, ip6Address));
        activeResponse.setCreatedDate(LocalDateTime.now());

        Mockito.when(instanceFeignService.getInstanceById(Mockito.any()))
                .thenReturn(buildingResponse)
                .thenReturn(buildingResponse)
                .thenReturn(buildingResponse)
                .thenReturn(activeResponse);
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
}
