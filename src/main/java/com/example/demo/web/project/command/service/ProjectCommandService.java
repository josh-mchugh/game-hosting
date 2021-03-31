package com.example.demo.web.project.command.service;

import com.example.demo.awx.host.aggregate.command.AwxHostDisableCommand;
import com.example.demo.awx.host.aggregate.command.AwxHostEnableCommand;
import com.example.demo.awx.host.feign.IHostFeignService;
import com.example.demo.awx.host.feign.model.HostPatchApi;
import com.example.demo.ovh.instance.feign.IInstanceFeignService;
import com.example.demo.web.project.command.service.model.ProjectInstanceStartRequest;
import com.example.demo.web.project.command.service.model.ProjectInstanceStopRequest;
import com.example.demo.web.project.projection.service.model.FetchAwxHostByInstanceOvhIdQuery;
import com.example.demo.web.project.projection.service.model.FetchAwxHostByInstanceOvhIdResponse;
import com.example.demo.web.project.projection.service.projection.AwxHostProjection;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class ProjectCommandService implements IProjectCommandService {

    private final IInstanceFeignService instanceFeignService;
    private final IHostFeignService hostFeignService;
    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    @Override
    public void handleProjectInstanceStart(ProjectInstanceStartRequest request) throws ExecutionException, InterruptedException {

        // Call OVH to start instance
        instanceFeignService.startInstance(request.getInstanceId());

        // Get AwxHost by OvH Instance Id
        AwxHostProjection awxHost = fetchAwxHostByInstanceId(request.getInstanceId());

        // Call AWX to set Host to enabled
        HostPatchApi updateBody = HostPatchApi.builder()
                .enabled(true)
                .build();
        hostFeignService.updateHost(awxHost.getAwxId(), updateBody);

        // Send command to enable AwxHost
        AwxHostEnableCommand command = new AwxHostEnableCommand(awxHost.getId());
        commandGateway.send(command);
    }

    @Override
    public void handleProjectInstanceStop(ProjectInstanceStopRequest request) throws ExecutionException, InterruptedException {

        // Call OVH to stop instance
        instanceFeignService.stopInstance(request.getInstanceId());

        // Get AwxHost by OvH Instance Id
        AwxHostProjection awxHost = fetchAwxHostByInstanceId(request.getInstanceId());

        // Call AWX to set Host to disabled
        HostPatchApi updateBody = HostPatchApi.builder()
                .enabled(false)
                .build();
        hostFeignService.updateHost(awxHost.getAwxId(), updateBody);

        // Send command to disable AwxHost
        AwxHostDisableCommand command = new AwxHostDisableCommand(awxHost.getId());
        commandGateway.send(command);
    }

    private AwxHostProjection fetchAwxHostByInstanceId(String instanceOvhId) throws ExecutionException, InterruptedException {

        FetchAwxHostByInstanceOvhIdQuery query = new FetchAwxHostByInstanceOvhIdQuery(instanceOvhId);
        FetchAwxHostByInstanceOvhIdResponse response = queryGateway.query(query, FetchAwxHostByInstanceOvhIdResponse.class).get();

        return response.getAwxHost();
    }
}
