package com.example.demo.web.project.dashboard.command;

import com.example.demo.awx.host.feign.HostFeignService;
import com.example.demo.awx.host.feign.model.HostPatchApi;
import com.example.demo.awx.host.service.AwxHostService;
import com.example.demo.awx.host.service.model.AwxHostDisableRequest;
import com.example.demo.awx.host.service.model.AwxHostEnableRequest;
import com.example.demo.ovh.instance.feign.InstanceFeignService;
import com.example.demo.web.project.dashboard.command.model.ProjectInstanceStartRequest;
import com.example.demo.web.project.dashboard.command.model.ProjectInstanceStopRequest;
import com.example.demo.web.project.dashboard.projection.ProjectDashboardService;
import com.example.demo.web.project.dashboard.projection.model.FetchAwxHostByInstanceOvhIdQuery;
import com.example.demo.web.project.dashboard.projection.model.FetchAwxHostByInstanceOvhIdResponse;
import com.example.demo.web.project.dashboard.projection.projection.AwxHostProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectCommandServiceImpl implements ProjectCommandService {

    private final InstanceFeignService instanceFeignService;
    private final HostFeignService hostFeignService;
    private final ProjectDashboardService projectDashboardService;
    private final AwxHostService awxHostService;

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
        AwxHostEnableRequest enableRequest = new AwxHostEnableRequest(awxHost.getId());
        awxHostService.handleEnable(enableRequest);
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
        AwxHostDisableRequest disableRequest = new AwxHostDisableRequest(awxHost.getId());
        awxHostService.handleDisable(disableRequest);
    }

    private AwxHostProjection fetchAwxHostByInstanceId(String instanceOvhId) {

        FetchAwxHostByInstanceOvhIdQuery query = new FetchAwxHostByInstanceOvhIdQuery(instanceOvhId);
        FetchAwxHostByInstanceOvhIdResponse response = projectDashboardService.fetchAwxHostByInstanceId(query);

        return response.getAwxHost();
    }
}
