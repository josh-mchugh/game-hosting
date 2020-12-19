package com.example.demo.web.dashboard.service;

import com.example.demo.awx.host.feign.HostFeignService;
import com.example.demo.awx.host.feign.model.HostApi;
import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.game.entity.GameType;
import com.example.demo.ovh.flavor.feign.model.FlavorApi;
import com.example.demo.ovh.image.feign.model.ImageApi;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.ovh.instance.feign.IInstanceFeignService;
import com.example.demo.ovh.instance.feign.IInstanceGroupFeignService;
import com.example.demo.ovh.instance.feign.model.InstanceApi;
import com.example.demo.ovh.instance.feign.model.InstanceGroupApi;
import com.example.demo.ovh.instance.feign.model.IpAddressApi;
import com.example.demo.project.aggregate.event.ProjectCreatedEvent;
import com.example.demo.project.entity.model.Project;
import com.example.demo.project.entity.service.IProjectService;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import com.example.demo.user.aggregate.event.UserVerifiedEvent;
import com.example.demo.user.entity.service.IUserService;
import com.example.demo.web.dashboard.service.model.DashboardDetailsResponse;
import com.example.demo.web.dashboard.service.model.DashboardProjectCreateRequest;
import com.example.demo.web.dashboard.service.model.DashboardProjectCreateResponse;
import com.example.demo.web.dashboard.service.projections.DashboardProjectProjection;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class DashboardServiceTest {

    @Autowired
    private IDashboardService dashboardService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    private ISessionUtil sessionUtil;

    @MockBean
    private IInstanceFeignService instanceFeignService;

    @MockBean
    private IInstanceGroupFeignService instanceGroupFeignService;

    @MockBean
    private HostFeignService hostFeignService;

    @MockBean
    private CommandGateway commandGateway;

    private SampleData data;

    @BeforeEach
    public void setup() {

        data = sampleBuilder.builder()
                .user()
                .region()
                .flavor()
                .image()
                .credential()
                .game()
                .awxOrganization()
                .awxCredential()
                .awxInventory()
                .awxProject()
                .awxPlaybook()
                .build();
    }

    @Test
    public void whenDashboardDetailsHasVerifiedUserThenReturnEmailVerifiedTrue() {

        UserVerifiedEvent event = UserVerifiedEvent.builder()
                .id(UUID.fromString(data.getUser().getId()))
                .verifiedDate(LocalDateTime.now())
                .build();

        userService.handleVerified(event);

        Mockito.when(sessionUtil.getCurrentUserEmail()).thenReturn(data.getUser().getEmail());

        DashboardDetailsResponse detailsResponse = dashboardService.getDashboardDetails();

        Assertions.assertTrue(detailsResponse.isEmailVerified());
    }

    @Test
    public void whenDashboardDetailsHasProjectsThenReturnProjectsTrue() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("name")
                .gameId(data.getGame().getId())
                .member(ProjectCreatedEvent.createMember(data.getUser().getId()))
                .build();

        projectService.handleCreated(event);

        Mockito.when(sessionUtil.getCurrentUserEmail()).thenReturn(data.getUser().getEmail());

        DashboardDetailsResponse detailsResponse = dashboardService.getDashboardDetails();

        Assertions.assertTrue(detailsResponse.isHasProjects());
    }

    @Test
    public void whenDashboardDetailsHasProjectsSizeThenReturnProjectsSize() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("name")
                .gameId(data.getGame().getId())
                .member(ProjectCreatedEvent.createMember(data.getUser().getId()))
                .build();

        projectService.handleCreated(event);

        Mockito.when(sessionUtil.getCurrentUserEmail()).thenReturn(data.getUser().getEmail());

        DashboardDetailsResponse detailsResponse = dashboardService.getDashboardDetails();

        Assertions.assertEquals(1, detailsResponse.getProjects().size());
    }

    @Test
    public void whenDashboardDetailsHasProjectThenProjectEqualsExpected() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("name")
                .gameId(data.getGame().getId())
                .member(ProjectCreatedEvent.createMember(data.getUser().getId()))
                .build();

        Project project = projectService.handleCreated(event);

        Mockito.when(sessionUtil.getCurrentUserEmail()).thenReturn(data.getUser().getEmail());

        DashboardProjectProjection expectedProject = new DashboardProjectProjection(project.getId(), project.getName(), data.getGame().getType());

        DashboardDetailsResponse detailsResponse = dashboardService.getDashboardDetails();

        Assertions.assertEquals(expectedProject, detailsResponse.getProjects().get(0));
    }

    @Test
    public void whenDashboardDetailsHasNonVerifiedUserThenReturnEmailVerifiedFalse() {

        Mockito.when(sessionUtil.getCurrentUserEmail()).thenReturn(data.getUser().getEmail());

        DashboardDetailsResponse detailsResponse = dashboardService.getDashboardDetails();

        Assertions.assertFalse(detailsResponse.isEmailVerified());
    }

    @Test
    public void whenDashboardDetailsHasNoProjectsThenReturnHasProjectsFalse() {

        Mockito.when(sessionUtil.getCurrentUserEmail()).thenReturn(data.getUser().getEmail());

        DashboardDetailsResponse detailsResponse = dashboardService.getDashboardDetails();

        Assertions.assertFalse(detailsResponse.isHasProjects());
    }

    @Test
    public void whenProjectCreateProjectIsValidThenReturnProjectId() {

        Mockito.when(sessionUtil.getCurrentUser()).thenReturn(data.getUser());

        Mockito.when(instanceGroupFeignService.createInstanceGroup(Mockito.any())).thenReturn(buildInstanceGroupApi());

        Mockito.when(instanceFeignService.createInstance(Mockito.any())).thenReturn(buildInstanceApi(InstanceStatus.BUILD));
        Mockito.when(instanceFeignService.getInstanceById(Mockito.anyString()))
                .thenReturn(buildInstanceApi(InstanceStatus.BUILD))
                .thenReturn(buildInstanceApi(InstanceStatus.ACTIVE));

        Mockito.when(hostFeignService.createHost(Mockito.any())).thenReturn(buildHostApi());

        Mockito.when(commandGateway.sendAndWait(Mockito.any())).thenReturn(UUID.randomUUID());

        DashboardProjectCreateRequest createRequest = DashboardProjectCreateRequest.builder()
                .name("test-project")
                .flavor("flavor-id")
                .region("region-id")
                .gameType(GameType.MINECRAFT_JAVA)
                .build();

        DashboardProjectCreateResponse response = dashboardService.handleDashboardProjectCreate(createRequest);

        Assertions.assertNotNull(response.getProjectId());
    }

    private InstanceGroupApi buildInstanceGroupApi() {

        InstanceGroupApi instanceGroupApi = new InstanceGroupApi();
        instanceGroupApi.setId("5eeb0772-7658-46e2-ad95-b2566ccdd394");
        instanceGroupApi.setName("d4afbaa3-0b2a-47a1-9c76-6dd2eab75042");
        instanceGroupApi.setRegion("US-EAST-VA-1");
        instanceGroupApi.setType("affinity");

        return instanceGroupApi;
    }

    private FlavorApi buildFlavorApi() {

        FlavorApi flavorApi = new FlavorApi();
        flavorApi.setId("a64381e7-c4e7-4b01-9fbe-da405c544d2e");
        flavorApi.setName("s1-2");
        flavorApi.setAvailable(true);
        flavorApi.setDisk(10);
        flavorApi.setInboundBandwidth(100);
        flavorApi.setOsType("linux");
        flavorApi.setOutboundBandwidth(100);
        flavorApi.setQuota(2);
        flavorApi.setRam(2000);
        flavorApi.setRegionName("US-EAST-VA-1");
        flavorApi.setType("ovh.vps-ssd");
        flavorApi.setVcpus(1);

        return flavorApi;
    }

    private ImageApi buildImageApi() {

        ImageApi imageApi = new ImageApi();
        imageApi.setId("cefc8220-ba0a-4327-b13d-591abaf4be0c");
        imageApi.setName("Ubuntu 20.04");
        imageApi.setCreationDate(LocalDateTime.now());
        imageApi.setMinDisk(0);
        imageApi.setMinRam(0);
        imageApi.setRegionName("US-EAST-VA-1");
        imageApi.setStatus("active");
        imageApi.setType("linux");
        imageApi.setUser("ubuntu");
        imageApi.setVisibility("public");

        return imageApi;
    }

    private IpAddressApi buildIpAddressApi(Integer version) {

        IpAddressApi ipAddressApi = new IpAddressApi();
        ipAddressApi.setIp("0.0.0.0.0");
        ipAddressApi.setVersion(version);

        return ipAddressApi;
    }

    private InstanceApi buildInstanceApi(InstanceStatus instanceStatus) {

        InstanceApi instanceApi = new InstanceApi();
        instanceApi.setId("b6625973-469d-42b4-8e4e-9ede2b3305bd");
        instanceApi.setName("Test Project Name");
        instanceApi.setStatus(instanceStatus);
        instanceApi.setCreatedDate(LocalDateTime.now());
        instanceApi.setRegion("US-EAST-VA-1");
        instanceApi.setIpAddresses(Arrays.asList(buildIpAddressApi(4), buildIpAddressApi(6)));

        instanceApi.setFlavor(buildFlavorApi());
        instanceApi.setImage(buildImageApi());

        return instanceApi;
    }

    private HostApi buildHostApi() {

        HostApi hostApi = new HostApi();
        hostApi.setId(1L);
        hostApi.setInventoryId(data.getAwxInventory().getAwxId());
        hostApi.setName("0.0.0.0.0");
        hostApi.setDescription("description");
        hostApi.setEnabled(true);

        return hostApi;
    }
}
