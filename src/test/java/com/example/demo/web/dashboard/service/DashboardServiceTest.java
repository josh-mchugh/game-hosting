package com.example.demo.web.dashboard.service;

import com.example.demo.awx.host.feign.HostClient;
import com.example.demo.awx.host.feign.model.HostApi;
import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.game.entity.GameType;
import com.example.demo.ovh.feign.common.IpAddressApi;
import com.example.demo.ovh.feign.common.SshKeyDetailApi;
import com.example.demo.ovh.flavor.feign.model.FlavorApi;
import com.example.demo.ovh.feign.image.model.ImageApi;
import com.example.demo.ovh.feign.instance.InstanceClient;
import com.example.demo.ovh.feign.instance.InstanceGroupClient;
import com.example.demo.ovh.feign.instance.model.InstanceApi;
import com.example.demo.ovh.feign.instance.model.InstanceGroupApi;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.project.model.Project;
import com.example.demo.project.service.IProjectService;
import com.example.demo.project.service.model.ProjectCreateRequest;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import com.example.demo.sample.util.TestProjectCreateRequest;
import com.example.demo.user.service.IUserService;
import com.example.demo.web.dashboard.service.model.DashboardDetailsResponse;
import com.example.demo.web.dashboard.service.model.DashboardProjectCreateRequest;
import com.example.demo.web.dashboard.service.model.DashboardProjectCreateResponse;
import com.example.demo.web.dashboard.service.projections.DashboardProjectProjection;
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
    private InstanceClient instanceClient;

    @MockBean
    private InstanceGroupClient instanceGroupClient;

    @MockBean
    private HostClient hostClient;

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

        userService.handleEmailVerification(data.getUser().getVerification().getToken());

        Mockito.when(sessionUtil.getCurrentUserEmail()).thenReturn(data.getUser().getEmail());

        DashboardDetailsResponse detailsResponse = dashboardService.getDashboardDetails();

        Assertions.assertTrue(detailsResponse.isEmailVerified());
    }

    @Test
    public void whenDashboardDetailsHasProjectsThenReturnProjectsTrue() {

        ProjectCreateRequest projectCreateRequest = TestProjectCreateRequest.builder()
                .name("test-project")
                .userId(data.getUser())
                .gameType(GameType.MINECRAFT_JAVA)
                .build();
        Project project = projectService.handleProjectCreate(projectCreateRequest);

        Mockito.when(sessionUtil.getCurrentUserEmail()).thenReturn(data.getUser().getEmail());

        DashboardDetailsResponse detailsResponse = dashboardService.getDashboardDetails();

        Assertions.assertTrue(detailsResponse.isHasProjects());
    }

    @Test
    public void whenDashboardDetailsHasProjectsSizeThenReturnProjectsSize() {

        ProjectCreateRequest projectCreateRequest = TestProjectCreateRequest.builder()
                .name("test-project")
                .userId(data.getUser())
                .gameType(data.getGame())
                .build();
        Project project = projectService.handleProjectCreate(projectCreateRequest);

        Mockito.when(sessionUtil.getCurrentUserEmail()).thenReturn(data.getUser().getEmail());

        DashboardDetailsResponse detailsResponse = dashboardService.getDashboardDetails();

        Assertions.assertEquals(1, detailsResponse.getProjects().size());
    }

    @Test
    public void whenDashboardDetailsHasProjectThenProjectEqualsExpected() {

        ProjectCreateRequest projectCreateRequest = TestProjectCreateRequest.builder()
                .name("test-project")
                .userId(data.getUser())
                .gameType(data.getGame())
                .build();
        Project project = projectService.handleProjectCreate(projectCreateRequest);

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

        Mockito.when(instanceGroupClient.createInstanceGroup(Mockito.anyString(), Mockito.any())).thenReturn(buildInstanceGroupApi());

        Mockito.when(instanceClient.createInstance(Mockito.anyString(), Mockito.any())).thenReturn(buildInstanceApi(InstanceStatus.BUILD));
        Mockito.when(instanceClient.getInstanceById(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(buildInstanceApi(InstanceStatus.BUILD))
                .thenReturn(buildInstanceApi(InstanceStatus.ACTIVE));

        Mockito.when(hostClient.createHost(Mockito.any())).thenReturn(buildHostApi());

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
        flavorApi.setFlavorId("a64381e7-c4e7-4b01-9fbe-da405c544d2e");
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
        imageApi.setImageId("cefc8220-ba0a-4327-b13d-591abaf4be0c");
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

    private SshKeyDetailApi buildSshKeyDetailApi() {

        SshKeyDetailApi sshKeyDetail = new SshKeyDetailApi();
        sshKeyDetail.setId("ssh key id");

        return sshKeyDetail;
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

        instanceApi.setSshKey(buildSshKeyDetailApi());
        instanceApi.setFlavor(buildFlavorApi());
        instanceApi.setImage(buildImageApi());

        return instanceApi;
    }

    private HostApi buildHostApi() {

        HostApi hostApi = new HostApi();
        hostApi.setId(1L);
        hostApi.setInventoryId(1L);
        hostApi.setInventoryId(data.getAwxInventory().getInventoryId());
        hostApi.setName("0.0.0.0.0");
        hostApi.setDescription("description");
        hostApi.setEnabled(true);

        return hostApi;
    }
}
