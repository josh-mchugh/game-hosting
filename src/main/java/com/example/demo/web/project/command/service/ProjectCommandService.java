package com.example.demo.web.project.command.service;

import com.example.demo.awx.host.aggregate.command.AwxHostDisableCommand;
import com.example.demo.awx.host.aggregate.command.AwxHostEnableCommand;
import com.example.demo.awx.host.feign.IHostFeignService;
import com.example.demo.awx.host.feign.model.HostPatchApi;
import com.example.demo.awx.host.projection.IAwxHostProjector;
import com.example.demo.awx.host.projection.model.AwxHostAwxIdProjection;
import com.example.demo.awx.host.projection.model.AwxHostAwxIdQuery;
import com.example.demo.ovh.instance.feign.IInstanceFeignService;
import com.example.demo.web.project.command.service.model.ProjectInstanceStartRequest;
import com.example.demo.web.project.command.service.model.ProjectInstanceStopRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProjectCommandService implements IProjectCommandService {

    private final IInstanceFeignService instanceFeignService;
    private final IAwxHostProjector awxHostProjector;
    private final IHostFeignService hostFeignService;
    private final CommandGateway commandGateway;

    @Override
    public void handleProjectInstanceStart(ProjectInstanceStartRequest request) {

        // Call OVH to start instance
        instanceFeignService.startInstance(request.getInstanceId());

        // Get AwxHost by OvH Instance Id
        AwxHostAwxIdQuery query = new AwxHostAwxIdQuery(request.getInstanceId());
        AwxHostAwxIdProjection projection = awxHostProjector.getHostIdProjection(query);

        // Call AWX to set Host to enabled
        HostPatchApi updateBody = HostPatchApi.builder()
                .enabled(true)
                .build();
        hostFeignService.updateHost(projection.getAwxId(), updateBody);

        // Send command to enable AwxHost
        AwxHostEnableCommand command = new AwxHostEnableCommand(UUID.fromString(projection.getAwxHostId()));
        commandGateway.send(command);
    }

    @Override
    public void handleProjectInstanceStop(ProjectInstanceStopRequest request) {

        // Call OVH to stop instance
        instanceFeignService.stopInstance(request.getInstanceId());

        // Get AwxHost by OvH Instance Id
        AwxHostAwxIdQuery query = new AwxHostAwxIdQuery(request.getInstanceId());
        AwxHostAwxIdProjection projection = awxHostProjector.getHostIdProjection(query);

        // Call AWX to set Host to disabled
        HostPatchApi updateBody = HostPatchApi.builder()
                .enabled(false)
                .build();
        hostFeignService.updateHost(projection.getAwxId(), updateBody);

        // Send command to disable AwxHost
        AwxHostDisableCommand command = new AwxHostDisableCommand(UUID.fromString(projection.getAwxHostId()));
        commandGateway.send(command);
    }
}
