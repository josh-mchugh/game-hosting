package com.example.demo.web.project.command.service;

import com.example.demo.awx.host.feign.IHostFeignService;
import com.example.demo.awx.host.feign.model.HostApi;
import com.example.demo.ovh.instance.feign.IInstanceFeignService;
import com.example.demo.web.project.command.service.model.ProjectInstanceStartRequest;
import com.example.demo.web.project.projection.service.model.FetchAwxHostByInstanceOvhIdQuery;
import com.example.demo.web.project.projection.service.model.FetchAwxHostByInstanceOvhIdResponse;
import com.example.demo.web.project.projection.service.projection.AwxHostProjection;
import feign.FeignException;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
public class ProjectCommandServiceInstanceStartTest {

    @MockBean
    private IHostFeignService hostFeignService;

    @Autowired
    private IProjectCommandService projectCommandService;

    @MockBean
    private IInstanceFeignService instanceFeignService;

    @MockBean
    private QueryGateway queryGateway;

    @Test
    public void whenHandleProjectInstanceStartHasValidIdThenThrowNoException() {

        String instanceOvhId = UUID.randomUUID().toString();
        Mockito.doNothing().when(instanceFeignService).startInstance(instanceOvhId);

        FetchAwxHostByInstanceOvhIdQuery query = new FetchAwxHostByInstanceOvhIdQuery(instanceOvhId);
        AwxHostProjection awxHostProjection = new AwxHostProjection(instanceOvhId, 1L);
        Mockito.when(queryGateway.query(query, FetchAwxHostByInstanceOvhIdResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchAwxHostByInstanceOvhIdResponse(awxHostProjection)));

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
