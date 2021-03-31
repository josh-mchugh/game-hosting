package com.example.demo.web.project.command.service;

import com.example.demo.awx.host.feign.IHostFeignService;
import com.example.demo.awx.host.feign.model.HostApi;
import com.example.demo.ovh.instance.feign.IInstanceFeignService;
import com.example.demo.web.project.command.service.model.ProjectInstanceStopRequest;
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
public class ProjectCommandServiceInstanceStopTest {

    @MockBean
    private IInstanceFeignService instanceFeignService;

    @MockBean
    private IHostFeignService hostFeignService;

    @Autowired
    private IProjectCommandService projectCommandService;

    @MockBean
    private QueryGateway queryGateway;

    @Test
    public void whenHandleProjectInstanceStopHasValidIdThenThrowNoException() {

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
        hostApi.setEnabled(false);

        Mockito.when(hostFeignService.updateHost(Mockito.anyLong(), Mockito.any())).thenReturn(hostApi);

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
