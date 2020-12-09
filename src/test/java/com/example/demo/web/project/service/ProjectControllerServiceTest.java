package com.example.demo.web.project.service;

import com.example.demo.awx.host.feign.HostFeignService;
import com.example.demo.awx.host.feign.model.HostApi;
import com.example.demo.ovh.instance.feign.InstanceClient;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import com.example.demo.web.project.service.model.ProjectDetails;
import com.example.demo.web.project.service.model.ProjectInstanceStartRequest;
import com.example.demo.web.project.service.model.ProjectInstanceStopRequest;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
public class ProjectControllerServiceTest {

    @Autowired
    private IProjectControllerService projectControllerService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    private InstanceClient instanceClient;

    @MockBean
    private HostFeignService hostFeignService;

    private SampleData data;

    @BeforeEach
    public void setup() {

        data = sampleBuilder.createDefault();
    }

    @Test
    public void whenGetProjectDetailsHasValidIdThenReturnProjectDetails() {

        ProjectDetails projectDetails = projectControllerService.getProjectDetails(data.getProject().getId());

        Assertions.assertNotNull(projectDetails);
    }

    @Test
    public void whenGetProjectDetailsHasNullIdThenThrowException() {

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> projectControllerService.getProjectDetails(null));

        Assertions.assertNotNull(exception);
    }

    @Test
    public void whenGetProjectDetailsHasInvalidIdThenThrowException() {

        Exception exception = Assertions.assertThrows(NullPointerException.class, () -> projectControllerService.getProjectDetails("123"));

        Assertions.assertNotNull(exception);
    }

    @Test
    public void whenHandleProjectInstanceStartHasValidIdThenThrowNoException() {

        Mockito.doNothing().when(instanceClient).startInstance(data.getProject().getId(), data.getInstance().getInstanceId());

        HostApi hostApi = new HostApi();
        hostApi.setId(1L);
        hostApi.setInventoryId(1L);
        hostApi.setName("0.0.0.0.0");
        hostApi.setDescription("test");
        hostApi.setEnabled(true);

        Mockito.when(hostFeignService.updateHost(Mockito.anyLong(), Mockito.any())).thenReturn(hostApi);

        ProjectInstanceStartRequest request = ProjectInstanceStartRequest.builder()
                .projectId(data.getProject().getId())
                .instanceId(data.getInstance().getInstanceId())
                .build();

        Assertions.assertDoesNotThrow(() -> projectControllerService.handleProjectInstanceStart(request));
    }

    @Test
    public void whenHandleProjectInstanceStartHasValidIdThenThrowException() {

        Mockito.doThrow(FeignException.FeignClientException.class).when(instanceClient).startInstance(Mockito.anyString(), Mockito.anyString());

        ProjectInstanceStartRequest request = ProjectInstanceStartRequest.builder()
                .projectId("asdf")
                .instanceId("asdf")
                .build();

        Assertions.assertThrows(FeignException.FeignClientException.class, () -> projectControllerService.handleProjectInstanceStart(request));
    }

    @Test
    public void whenHandleProjectInstanceStopHasValidIdThenThrowNoException() {

        Mockito.doNothing().when(instanceClient).startInstance(data.getProject().getId(), data.getInstance().getInstanceId());

        HostApi hostApi = new HostApi();
        hostApi.setId(1L);
        hostApi.setInventoryId(1L);
        hostApi.setName("0.0.0.0.0");
        hostApi.setDescription("test");
        hostApi.setEnabled(false);

        Mockito.when(hostFeignService.updateHost(Mockito.anyLong(), Mockito.any())).thenReturn(hostApi);

        ProjectInstanceStopRequest request = ProjectInstanceStopRequest.builder()
                .projectId(data.getProject().getId())
                .instanceId(data.getInstance().getInstanceId())
                .build();

        Assertions.assertDoesNotThrow(() -> projectControllerService.handleProjectInstanceStop(request));
    }

    @Test
    public void whenHandleProjectInstanceStopHasValidIdThenThrowException() {

        Mockito.doThrow(FeignException.FeignClientException.class).when(instanceClient).stopInstance(Mockito.anyString(), Mockito.anyString());

        ProjectInstanceStopRequest request = ProjectInstanceStopRequest.builder()
                .projectId("asdf")
                .instanceId("asdf")
                .build();

        Assertions.assertThrows(FeignException.FeignClientException.class, () -> projectControllerService.handleProjectInstanceStop(request));
    }
}
