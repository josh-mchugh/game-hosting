package com.example.demo.project.aggregate.saga;

import com.dyngr.Polling;
import com.dyngr.core.AttemptResult;
import com.dyngr.core.AttemptResults;
import com.example.demo.ovh.credential.projector.ICredentialProjector;
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
import com.example.demo.project.aggregate.event.ProjectCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Saga
@Slf4j
public class ProjectCreatedSaga {

    @Autowired
    private transient IInstanceGroupFeignService instanceGroupFeignService;

    @Autowired
    private transient IInstanceFeignService instanceFeignService;

    @Autowired
    private transient ICredentialProjector credentialProjector;

    @Autowired
    private transient CommandGateway commandGateway;

    private LocalDateTime startTime;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handle(ProjectCreatedEvent event) {

        startTime = LocalDateTime.now();
        log.info("Project Created Saga Started: {}", startTime);

        InstanceGroupCreateApi groupCreateRequest = InstanceGroupCreateApi.builder()
                .name(event.getId().toString())
                .region("US-EAST-VA-1")
                .type("affinity")
                .build();

        InstanceGroupApi instanceGroupApi = instanceGroupFeignService.createInstanceGroup(groupCreateRequest);

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

        String sshKeyId = credentialProjector.getAnsibleOvhId();

        InstanceCreateApi ovhInstanceCreateRequest = InstanceCreateApi.builder()
                .name(event.getProjectId().toString())
                .flavorId("a64381e7-c4e7-4b01-9fbe-da405c544d2e")
                .imageId("03155da7-81d5-4eb1-9254-c2869d1c5a14")
                .region("US-EAST-VA-1")
                .groupId(event.getOvhId())
                .sshKeyId(sshKeyId)
                .build();

        InstanceApi instanceApi = instanceFeignService.createInstance(ovhInstanceCreateRequest);

        UUID id = UUID.randomUUID();
        UUID flavorId = UUID.fromString("028b1da5-6230-4cc1-ace9-3aa38e480fd7");
        UUID imageId = UUID.fromString("cbd82bea-1c79-4efd-be62-0c965dbe2f11");
        UUID credentialId = UUID.fromString("0f60138e-1318-4dce-8c60-d5dd6e68d03d");
        SagaLifecycle.associateWith("id", id.toString());

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .id(id)
                .flavorId(flavorId)
                .imageId(imageId)
                .credentialId(credentialId)
                .instanceGroupId(event.getId())
                .name(instanceApi.getName())
                .ovhId(instanceApi.getId())
                .status(instanceApi.getStatus())
                .instanceCreatedDate(instanceApi.getCreatedDate())
                .build();

        commandGateway.send(command);
    }

    // TODO: Add Instance To AwxHost

    @EndSaga
    @SagaEventHandler(associationProperty = "id")
    public void handle(InstanceCreatedEvent event) {

        Polling.waitPeriodly(1, TimeUnit.SECONDS)
                .stopAfterAttempt(60)
                .stopIfException(false)
                .run(() -> this.handleInstancePolling(event));

        log.info("Finished handleDashboardProjectCreate. Total Time: {}s", Duration.between(startTime, LocalDateTime.now()).toSeconds());
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
}
