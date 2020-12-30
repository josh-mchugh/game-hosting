package com.example.demo.web.project.command.service;

import com.example.demo.awx.host.feign.IHostFeignService;
import com.example.demo.awx.host.feign.model.HostApi;
import com.example.demo.awx.host.projection.IAwxHostProjector;
import com.example.demo.awx.host.projection.model.AwxHostAwxIdProjection;
import com.example.demo.awx.host.projection.model.AwxHostAwxIdQuery;
import com.example.demo.ovh.instance.feign.IInstanceFeignService;
import com.example.demo.web.project.command.service.model.ProjectInstanceStopRequest;
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
    private IInstanceFeignService instanceFeignService;

    @MockBean
    private IAwxHostProjector awxHostProjector;

    @MockBean
    private IHostFeignService hostFeignService;

    @Autowired
    private IProjectCommandService projectCommandService;

    @Test
    public void whenHandleProjectInstanceStopHasValidIdThenThrowNoException() {

        Mockito.doNothing().when(instanceFeignService).startInstance("instanceOvhId");

        AwxHostAwxIdProjection mockAwxHostAwxIdProjection = new AwxHostAwxIdProjection(UUID.randomUUID().toString(), 1L);
        Mockito.when(awxHostProjector.getHostIdProjection(Mockito.any(AwxHostAwxIdQuery.class))).thenReturn(mockAwxHostAwxIdProjection);

        HostApi hostApi = new HostApi();
        hostApi.setId(1L);
        hostApi.setInventoryId(1L);
        hostApi.setName("0.0.0.0.0");
        hostApi.setDescription("test");
        hostApi.setEnabled(false);

        Mockito.when(hostFeignService.updateHost(Mockito.anyLong(), Mockito.any())).thenReturn(hostApi);

        ProjectInstanceStopRequest request = ProjectInstanceStopRequest.builder()
                .projectId("projectId")
                .instanceId("instanceOvhId")
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
