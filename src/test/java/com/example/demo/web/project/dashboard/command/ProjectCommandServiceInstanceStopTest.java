package com.example.demo.web.project.dashboard.command;

import com.example.demo.awx.host.entity.model.AwxHost;
import com.example.demo.awx.host.feign.HostFeignService;
import com.example.demo.awx.host.feign.model.HostApi;
import com.example.demo.awx.host.service.AwxHostService;
import com.example.demo.awx.host.service.model.AwxHostDisableRequest;
import com.example.demo.ovh.instance.feign.InstanceFeignService;
import com.example.demo.web.project.dashboard.command.model.ProjectInstanceStopRequest;
import com.example.demo.web.project.dashboard.projection.ProjectDashboardService;
import com.example.demo.web.project.dashboard.projection.model.FetchAwxHostByInstanceOvhIdQuery;
import com.example.demo.web.project.dashboard.projection.model.FetchAwxHostByInstanceOvhIdResponse;
import com.example.demo.web.project.dashboard.projection.projection.AwxHostProjection;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.UUID;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
public class ProjectCommandServiceInstanceStopTest {

    @MockBean
    private InstanceFeignService instanceFeignService;

    @MockBean
    private HostFeignService hostFeignService;

    @Autowired
    private ProjectCommandService projectCommandService;

    @MockBean
    private ProjectDashboardService projectDashboardService;

    @MockBean
    private AwxHostService awxHostService;

    @Test
    public void whenHandleProjectInstanceStopHasValidIdThenThrowNoException() {

        String instanceOvhId = UUID.randomUUID().toString();
        Mockito.doNothing().when(instanceFeignService).stopInstance(instanceOvhId);

        FetchAwxHostByInstanceOvhIdQuery query = new FetchAwxHostByInstanceOvhIdQuery(instanceOvhId);
        AwxHostProjection awxHostProjection = new AwxHostProjection(instanceOvhId, 1L);
        Mockito.when(projectDashboardService.fetchAwxHostByInstanceId(query))
                .thenReturn(new FetchAwxHostByInstanceOvhIdResponse(awxHostProjection));

        HostApi hostApi = new HostApi();
        hostApi.setId(1L);
        hostApi.setInventoryId(1L);
        hostApi.setName("0.0.0.0.0");
        hostApi.setDescription("test");
        hostApi.setEnabled(false);

        Mockito.when(hostFeignService.updateHost(Mockito.anyLong(), Mockito.any())).thenReturn(hostApi);
        Mockito.when(awxHostService.handleDisable(Mockito.any(AwxHostDisableRequest.class))).thenReturn(AwxHost.builder().build());

        ProjectInstanceStopRequest request = ProjectInstanceStopRequest.builder()
                .projectId("projectId")
                .instanceId(instanceOvhId)
                .build();

        Assertions.assertDoesNotThrow(() -> projectCommandService.handleProjectInstanceStop(request));
    }

    @Test
    public void whenHandleProjectInstanceStopHasValidIdThenThrowException() {

        Mockito.doThrow(FeignException.FeignClientException.class).when(instanceFeignService).stopInstance(Mockito.anyString());

        ProjectInstanceStopRequest request = ProjectInstanceStopRequest.builder()
                .projectId("asdf")
                .instanceId("asdf")
                .build();

        Assertions.assertThrows(FeignException.FeignClientException.class, () -> projectCommandService.handleProjectInstanceStop(request));
    }
}
