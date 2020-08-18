package com.example.demo.web.dashboard.service;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.game.entity.GameType;
import com.example.demo.ovh.feign.common.SshKeyDetailApi;
import com.example.demo.ovh.feign.flavor.model.FlavorApi;
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

        InstanceGroupApi instanceGroupApiResponse = new InstanceGroupApi();
        instanceGroupApiResponse.setId("5eeb0772-7658-46e2-ad95-b2566ccdd394");
        instanceGroupApiResponse.setName("d4afbaa3-0b2a-47a1-9c76-6dd2eab75042");
        instanceGroupApiResponse.setRegion("US-EAST-VA-1");
        instanceGroupApiResponse.setType("affinity");

        Mockito.when(instanceGroupClient.createInstanceGroup(Mockito.anyString(), Mockito.any())).thenReturn(instanceGroupApiResponse);

        InstanceApi instanceApiResponse = new InstanceApi();
        instanceApiResponse.setId("b6625973-469d-42b4-8e4e-9ede2b3305bd");
        instanceApiResponse.setName("Test Project Name");
        instanceApiResponse.setStatus(InstanceStatus.BUILD);
        instanceApiResponse.setCreatedDate(LocalDateTime.now());
        instanceApiResponse.setRegion("US-EAST-VA-1");

        SshKeyDetailApi sshKeyDetail = new SshKeyDetailApi();
        sshKeyDetail.setId("ssh key id");

        instanceApiResponse.setSshKey(sshKeyDetail);

        FlavorApi flavorApiResponse = new FlavorApi();
        flavorApiResponse.setFlavorId("a64381e7-c4e7-4b01-9fbe-da405c544d2e");
        flavorApiResponse.setName("s1-2");
        flavorApiResponse.setAvailable(true);
        flavorApiResponse.setDisk(10);
        flavorApiResponse.setInboundBandwidth(100);
        flavorApiResponse.setOsType("linux");
        flavorApiResponse.setOutboundBandwidth(100);
        flavorApiResponse.setQuota(2);
        flavorApiResponse.setRam(2000);
        flavorApiResponse.setRegionName("US-EAST-VA-1");
        flavorApiResponse.setType("ovh.vps-ssd");
        flavorApiResponse.setVcpus(1);

        instanceApiResponse.setFlavor(flavorApiResponse);

        ImageApi imageApiResponse = new ImageApi();
        imageApiResponse.setImageId("cefc8220-ba0a-4327-b13d-591abaf4be0c");
        imageApiResponse.setName("Ubuntu 20.04");
        imageApiResponse.setCreationDate(LocalDateTime.now());
        imageApiResponse.setMinDisk(0);
        imageApiResponse.setMinRam(0);
        imageApiResponse.setRegionName("US-EAST-VA-1");
        imageApiResponse.setStatus("active");
        imageApiResponse.setType("linux");
        imageApiResponse.setUser("ubuntu");
        imageApiResponse.setVisibility("public");

        instanceApiResponse.setImage(imageApiResponse);

        Mockito.when(instanceClient.createInstance(Mockito.anyString(), Mockito.any())).thenReturn(instanceApiResponse);

        DashboardProjectCreateRequest createRequest = DashboardProjectCreateRequest.builder()
                .name("test-project")
                .flavor("flavor-id")
                .region("region-id")
                .gameType(GameType.MINECRAFT_JAVA)
                .build();

        DashboardProjectCreateResponse response = dashboardService.handleDashboardProjectCreate(createRequest);

        Assertions.assertNotNull(response.getProjectId());
    }
}
