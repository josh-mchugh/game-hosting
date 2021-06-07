package com.example.demo.project.aggregate.saga;

import com.dyngr.Polling;
import com.dyngr.core.AttemptResult;
import com.dyngr.core.AttemptResults;
import com.example.demo.ovh.instance.aggregate.command.InstanceCreateCommand;
import com.example.demo.ovh.instance.aggregate.command.InstanceGroupCreateCommand;
import com.example.demo.ovh.instance.aggregate.command.InstanceUpdateCommand;
import com.example.demo.ovh.instance.aggregate.event.InstanceCreatedEvent;
import com.example.demo.ovh.instance.aggregate.event.InstanceGroupCreatedEvent;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.ovh.instance.feign.IInstanceFeignService;
import com.example.demo.ovh.instance.feign.IInstanceGroupFeignService;
import com.example.demo.ovh.instance.feign.model.InstanceApi;
import com.example.demo.ovh.instance.feign.model.InstanceCreateApi;
import com.example.demo.ovh.instance.feign.model.InstanceGroupApi;
import com.example.demo.ovh.instance.feign.model.InstanceGroupCreateApi;
import com.example.demo.project.aggregate.command.ProjectStateCreateInstanceUpdateCommand;
import com.example.demo.project.aggregate.event.ProjectBillingAddedEvent;
import com.example.demo.project.aggregate.event.ProjectStateCreateInstanceUpdatedEvent;
import com.example.demo.project.aggregate.saga.projection.model.FetchAnsibleCredentialQuery;
import com.example.demo.project.aggregate.saga.projection.model.FetchAnsibleCredentialResponse;
import com.example.demo.project.aggregate.saga.projection.model.FetchProjectCreateInstanceDetailsQuery;
import com.example.demo.project.aggregate.saga.projection.model.FetchProjectCreateInstanceDetailsResponse;
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
import java.util.concurrent.TimeUnit;

@Saga
@Slf4j
public class ProjectBuildSaga {

    @Autowired
    private transient IInstanceGroupFeignService instanceGroupFeignService;

    @Autowired
    private transient IInstanceFeignService instanceFeignService;

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

    @SagaEventHandler(associationProperty = "id")
    public void handle(ProjectStateCreateInstanceUpdatedEvent event) throws ExecutionException, InterruptedException {

        FetchAnsibleCredentialResponse credentialResponse = fetchAnsibleCredentialIds();

        FetchProjectCreateInstanceDetailsQuery instanceDetailsQuery = new FetchProjectCreateInstanceDetailsQuery(event.getId());
        FetchProjectCreateInstanceDetailsResponse instanceDetailsResponse = queryGateway.query(instanceDetailsQuery, FetchProjectCreateInstanceDetailsResponse.class).get();

        InstanceCreateApi ovhInstanceCreateRequest = InstanceCreateApi.builder()
                .name(event.getId().toString())
                .flavorId(instanceDetailsResponse.getFlavorOvhId())
                .imageId(instanceDetailsResponse.getImageOvhId())
                .region(instanceDetailsResponse.getRegionName())
                .groupId(instanceDetailsResponse.getInstanceGroupOvhId())
                .sshKeyId(credentialResponse.getOvhId())
                .build();

        InstanceApi instanceApi = instanceFeignService.createInstance(ovhInstanceCreateRequest);

        UUID id = UUID.randomUUID();
        SagaLifecycle.associateWith("id", id.toString());

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .id(id)
                .flavorId(instanceDetailsResponse.getFlavorId())
                .imageId(instanceDetailsResponse.getImageId())
                .credentialId(credentialResponse.getId())
                .instanceGroupId(instanceDetailsResponse.getInstanceGroupId())
                .name(instanceApi.getName())
                .ovhId(instanceApi.getId())
                .status(instanceApi.getStatus())
                .instanceCreatedDate(instanceApi.getCreatedDate())
                .build();

        commandGateway.send(command);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "id")
    public void handle(InstanceCreatedEvent event) {

        Polling.waitPeriodly(1, TimeUnit.SECONDS)
                .stopAfterAttempt(60)
                .stopIfException(false)
                .run(() -> this.handleInstancePolling(event));

        log.info("Saga complete: {}s", ChronoUnit.SECONDS.between(startTime, LocalDateTime.now()));
    }

    private AttemptResult<?> handleInstancePolling(InstanceCreatedEvent event) {

        InstanceApi instanceApi =  instanceFeignService.getInstanceById(event.getOvhId());

        if(instanceApi.getStatus().equals(InstanceStatus.ACTIVE)) {

            InstanceUpdateCommand command = InstanceUpdateCommand.builder()
                    .id(event.getId())
                    .name(instanceApi.getName())
                    .status(instanceApi.getStatus())
                    .ip4Address(instanceApi.getIp4Address())
                    .ip6Address(instanceApi.getIp6Address())
                    .instanceCreatedDate(instanceApi.getCreatedDate())
                    .build();

            commandGateway.send(command);

            return AttemptResults.justFinish();
        }

        return AttemptResults.justContinue();
    }

    private String fetchRegionNameById(UUID id) throws ExecutionException, InterruptedException {

        FetchProjectRegionNameByIdQuery query = new FetchProjectRegionNameByIdQuery(id);
        FetchProjectRegionNameByIdResponse response = queryGateway.query(query, FetchProjectRegionNameByIdResponse.class).get();

        return response.getRegionName();
    }

    private FetchAnsibleCredentialResponse fetchAnsibleCredentialIds() throws ExecutionException, InterruptedException {

        FetchAnsibleCredentialQuery query = new FetchAnsibleCredentialQuery();
        FetchAnsibleCredentialResponse response = queryGateway.query(query, FetchAnsibleCredentialResponse.class).get();

        return response;
    }
}
