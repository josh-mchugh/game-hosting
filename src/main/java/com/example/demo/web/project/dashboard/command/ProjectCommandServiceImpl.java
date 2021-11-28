package com.example.demo.web.project.dashboard.command;

import com.example.demo.awx.host.aggregate.command.AwxHostDisableCommand;
import com.example.demo.awx.host.aggregate.command.AwxHostEnableCommand;
import com.example.demo.awx.host.feign.HostFeignService;
import com.example.demo.awx.host.feign.model.HostPatchApi;
import com.example.demo.ovh.instance.feign.InstanceFeignService;
import com.example.demo.web.project.dashboard.command.model.ProjectInstanceStartRequest;
import com.example.demo.web.project.dashboard.command.model.ProjectInstanceStopRequest;
import com.example.demo.web.project.dashboard.projection.ProjectDashboardService;
import com.example.demo.web.project.dashboard.projection.model.FetchAwxHostByInstanceOvhIdQuery;
import com.example.demo.web.project.dashboard.projection.model.FetchAwxHostByInstanceOvhIdResponse;
import com.example.demo.web.project.dashboard.projection.projection.AwxHostProjection;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectCommandServiceImpl implements ProjectCommandService {

    private final InstanceFeignService instanceFeignService;
    private final HostFeignService hostFeignService;
    private final ProjectDashboardService projectDashboardService;
    private final CommandGateway commandGateway;

    @Override
    public void handleProjectInstanceStart(ProjectInstanceStartRequest request) {

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
    public void handleProjectInstanceStop(ProjectInstanceStopRequest request) {

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

    private AwxHostProjection fetchAwxHostByInstanceId(String instanceOvhId) {

        FetchAwxHostByInstanceOvhIdQuery query = new FetchAwxHostByInstanceOvhIdQuery(instanceOvhId);
        FetchAwxHostByInstanceOvhIdResponse response = projectDashboardService.fetchAwxHostByInstanceId(query);

        return response.getAwxHost();
    }
}
