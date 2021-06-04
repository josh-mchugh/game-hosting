package com.example.demo.project.aggregate.saga;

import com.example.demo.ovh.instance.aggregate.command.InstanceGroupCreateCommand;
import com.example.demo.ovh.instance.aggregate.event.InstanceGroupCreatedEvent;
import com.example.demo.ovh.instance.feign.IInstanceGroupFeignService;
import com.example.demo.ovh.instance.feign.model.InstanceGroupApi;
import com.example.demo.ovh.instance.feign.model.InstanceGroupCreateApi;
import com.example.demo.project.aggregate.command.ProjectStateCreateInstanceUpdateCommand;
import com.example.demo.project.aggregate.event.ProjectBillingAddedEvent;
import com.example.demo.project.aggregate.event.ProjectStateCreateInstanceUpdatedEvent;
import com.example.demo.project.aggregate.saga.projection.model.FetchProjectRegionNameByIdQuery;
import com.example.demo.project.aggregate.saga.projection.model.FetchProjectRegionNameByIdResponse;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Saga
@Slf4j
public class ProjectBuildSaga {

    @Autowired
    private transient IInstanceGroupFeignService instanceGroupFeignService;

    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private transient QueryGateway queryGateway;

    private LocalDateTime startTime;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handle(ProjectBillingAddedEvent event) throws ExecutionException, InterruptedException {

        startTime = LocalDateTime.now();

        String regionName = fetchRegionNameById(event.getId());

        InstanceGroupCreateApi instanceGroupCreateApi = InstanceGroupCreateApi.builder()
                .name(event.getId().toString())
                .region(regionName)
                .type("affinity")
                .build();

        InstanceGroupApi instanceGroupApi = instanceGroupFeignService.createInstanceGroup(instanceGroupCreateApi);

        UUID id = UUID.randomUUID();
        SagaLifecycle.associateWith("id", id.toString());

        InstanceGroupCreateCommand command = InstanceGroupCreateCommand.builder()
                .id(id)
                .projectId(event.getId())
                .ovhId(instanceGroupApi.getId())
                .name(instanceGroupApi.getName())
                .type(instanceGroupApi.getType())
                .build();

        commandGateway.send(command);
    }

    @SagaEventHandler(associationProperty = "id")
    public void handle(InstanceGroupCreatedEvent event) {

        SagaLifecycle.associateWith("id", event.getProjectId().toString());

        commandGateway.send(new ProjectStateCreateInstanceUpdateCommand(event.getProjectId()));
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "id")
    public void handle(ProjectStateCreateInstanceUpdatedEvent event) {

        log.info("Saga complete: {}s", ChronoUnit.SECONDS.between(startTime, LocalDateTime.now()));
    }

    private String fetchRegionNameById(UUID id) throws ExecutionException, InterruptedException {

        FetchProjectRegionNameByIdQuery query = new FetchProjectRegionNameByIdQuery(id);
        FetchProjectRegionNameByIdResponse response = queryGateway.query(query, FetchProjectRegionNameByIdResponse.class).get();

        return response.getRegionName();
    }
}
