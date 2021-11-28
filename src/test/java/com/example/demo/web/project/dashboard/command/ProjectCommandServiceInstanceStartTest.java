package com.example.demo.web.project.dashboard.command;

import com.example.demo.awx.host.feign.HostFeignService;
import com.example.demo.awx.host.feign.model.HostApi;
import com.example.demo.ovh.instance.feign.InstanceFeignService;
import com.example.demo.web.project.dashboard.command.model.ProjectInstanceStartRequest;
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
public class ProjectCommandServiceInstanceStartTest {

    @MockBean
    private HostFeignService hostFeignService;

    @Autowired
    private ProjectCommandService projectCommandService;

    @MockBean
    private InstanceFeignService instanceFeignService;

    @MockBean
    private ProjectDashboardService projectProjectionService;

    @Test
    public void whenHandleProjectInstanceStartHasValidIdThenThrowNoException() {

        String instanceOvhId = UUID.randomUUID().toString();
        Mockito.doNothing().when(instanceFeignService).startInstance(instanceOvhId);

        FetchAwxHostByInstanceOvhIdQuery query = new FetchAwxHostByInstanceOvhIdQuery(instanceOvhId);
        AwxHostProjection awxHostProjection = new AwxHostProjection(instanceOvhId, 1L);
        Mockito.when(projectProjectionService.fetchAwxHostByInstanceId(query))
                .thenReturn(new FetchAwxHostByInstanceOvhIdResponse(awxHostProjection));

        HostApi hostApi = new HostApi();
        hostApi.setId(1L);
        hostApi.setInventoryId(1L);
        hostApi.setName("0.0.0.0.0");
        hostApi.setDescription("test");
        hostApi.setEnabled(true);

        Mockito.when(hostFeignService.updateHost(Mockito.anyLong(), Mockito.any())).thenReturn(hostApi);

        ProjectInstanceStartRequest request = ProjectInstanceStartRequest.builder()
                .projectId("projectId")
                .instanceId(instanceOvhId)
                .build();

        Assertions.assertDoesNotThrow(() -> projectCommandService.handleProjectInstanceStart(request));
    }

    @Test
    public void whenHandleProjectInstanceStartHasValidIdThenThrowException() {

        Mockito.doThrow(FeignException.FeignClientException.class).when(instanceFeignService).startInstance(Mockito.anyString());

        ProjectInstanceStartRequest request = ProjectInstanceStartRequest.builder()
                .projectId("asdf")
                .instanceId("asdf")
                .build();

        Assertions.assertThrows(FeignException.FeignClientException.class, () -> projectCommandService.handleProjectInstanceStart(request));
    }
}
