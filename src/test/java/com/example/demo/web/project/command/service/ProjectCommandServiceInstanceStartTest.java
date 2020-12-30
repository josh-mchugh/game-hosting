package com.example.demo.web.project.command.service;

import com.example.demo.awx.host.feign.IHostFeignService;
import com.example.demo.awx.host.feign.model.HostApi;
import com.example.demo.awx.host.projection.IAwxHostProjector;
import com.example.demo.awx.host.projection.model.AwxHostAwxIdProjection;
import com.example.demo.awx.host.projection.model.AwxHostAwxIdQuery;
import com.example.demo.ovh.instance.feign.IInstanceFeignService;
import com.example.demo.web.project.command.service.model.ProjectInstanceStartRequest;
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
    private IInstanceFeignService instanceFeignService;

    @MockBean
    private IAwxHostProjector awxHostProjector;

    @MockBean
    private IHostFeignService hostFeignService;

    @Autowired
    private IProjectCommandService projectCommandService;

    @Test
    public void whenHandleProjectInstanceStartHasValidIdThenThrowNoException() {

        Mockito.doNothing().when(instanceFeignService).startInstance("instanceOvhId");

        AwxHostAwxIdProjection mockAwxHostAwxIdProjection = new AwxHostAwxIdProjection(UUID.randomUUID().toString(), 1L);
        Mockito.when(awxHostProjector.getHostIdProjection(Mockito.any(AwxHostAwxIdQuery.class))).thenReturn(mockAwxHostAwxIdProjection);

        HostApi hostApi = new HostApi();
        hostApi.setId(1L);
        hostApi.setInventoryId(1L);
        hostApi.setName("0.0.0.0.0");
        hostApi.setDescription("test");
        hostApi.setEnabled(true);

        Mockito.when(hostFeignService.updateHost(Mockito.anyLong(), Mockito.any())).thenReturn(hostApi);

        ProjectInstanceStartRequest request = ProjectInstanceStartRequest.builder()
                .projectId("projectId")
                .instanceId("instanceOvhId")
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
