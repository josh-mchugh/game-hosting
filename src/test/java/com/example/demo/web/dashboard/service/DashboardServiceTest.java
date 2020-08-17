package com.example.demo.web.dashboard.service;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.game.entity.GameType;
import com.example.demo.game.model.Game;
import com.example.demo.game.service.IGameService;
import com.example.demo.game.service.model.GameCreateRequest;
import com.example.demo.ovh.credential.model.Credential;
import com.example.demo.ovh.credential.service.ICredentialService;
import com.example.demo.ovh.credential.service.model.CredentialCreateRequest;
import com.example.demo.ovh.feign.OvhClient;
import com.example.demo.ovh.feign.model.OvhFlavorApiResponse;
import com.example.demo.ovh.feign.model.OvhImageApiResponse;
import com.example.demo.ovh.feign.model.OvhInstanceApiResponse;
import com.example.demo.ovh.feign.model.OvhInstanceGroupApiResponse;
import com.example.demo.ovh.feign.model.OvhSshKeyDetail;
import com.example.demo.ovh.flavor.model.Flavor;
import com.example.demo.ovh.flavor.service.IFlavorService;
import com.example.demo.ovh.flavor.service.model.FlavorCreateRequest;
import com.example.demo.ovh.image.model.Image;
import com.example.demo.ovh.image.service.IImageService;
import com.example.demo.ovh.image.service.model.ImageCreateRequest;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.ovh.region.model.Region;
import com.example.demo.ovh.region.service.IRegionService;
import com.example.demo.ovh.region.service.model.RegionCreateRequest;
import com.example.demo.project.model.Project;
import com.example.demo.project.service.IProjectService;
import com.example.demo.project.service.model.ProjectCreateRequest;
import com.example.demo.sample.TestCredential;
import com.example.demo.sample.TestFlavorUtil;
import com.example.demo.sample.TestGameUtil;
import com.example.demo.sample.TestImageUtil;
import com.example.demo.sample.TestProjectUtil;
import com.example.demo.sample.TestRegionUtil;
import com.example.demo.sample.TestUserUtil;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserCreateRequest;
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
    private IRegionService regionService;

    @Autowired
    private IFlavorService flavorService;

    @Autowired
    private IImageService imageService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IGameService gameService;

    @Autowired
    private ICredentialService credentialService;

    @MockBean
    private ISessionUtil sessionUtil;

    @MockBean
    private OvhClient ovhClient;

    private User user;

    @BeforeEach
    public void setup() {
        UserCreateRequest userCreateRequest = TestUserUtil.createUser("test@test");
        user = userService.handleCreateUser(userCreateRequest);
    }

    @Test
    public void whenDashboardDetailsHasVerifiedUserThenReturnEmailVerifiedTrue() {

        userService.handleEmailVerification(user.getVerification().getToken());

        Mockito.when(sessionUtil.getCurrentUserEmail()).thenReturn(user.getEmail());

        DashboardDetailsResponse detailsResponse = dashboardService.getDashboardDetails();

        Assertions.assertTrue(detailsResponse.isEmailVerified());
    }

    @Test
    public void whenDashboardDetailsHasProjectsThenReturnProjectsTrue() {

        ProjectCreateRequest projectCreateRequest = TestProjectUtil.builder()
                .name("test-project")
                .userId(user)
                .gameType(GameType.MINECRAFT_JAVA)
                .build();
        Project project = projectService.handleProjectCreate(projectCreateRequest);

        Mockito.when(sessionUtil.getCurrentUserEmail()).thenReturn(user.getEmail());

        DashboardDetailsResponse detailsResponse = dashboardService.getDashboardDetails();

        Assertions.assertTrue(detailsResponse.isHasProjects());
    }

    @Test
    public void whenDashboardDetailsHasProjectsSizeThenReturnProjectsSize() {

        GameCreateRequest gameCreateRequest = TestGameUtil.builder().build();
        Game game =  gameService.handleGameCreateRequest(gameCreateRequest);

        ProjectCreateRequest projectCreateRequest = TestProjectUtil.builder()
                .name("test-project")
                .userId(user)
                .gameType(game)
                .build();
        Project project = projectService.handleProjectCreate(projectCreateRequest);

        Mockito.when(sessionUtil.getCurrentUserEmail()).thenReturn(user.getEmail());

        DashboardDetailsResponse detailsResponse = dashboardService.getDashboardDetails();

        Assertions.assertEquals(1, detailsResponse.getProjects().size());
    }

    @Test
    public void whenDashboardDetailsHasProjectThenProjectEqualsExpected() {

        GameCreateRequest gameCreateRequest = TestGameUtil.builder().build();
        Game game =  gameService.handleGameCreateRequest(gameCreateRequest);

        ProjectCreateRequest projectCreateRequest = TestProjectUtil.builder()
                .name("test-project")
                .userId(user)
                .gameType(game)
                .build();
        Project project = projectService.handleProjectCreate(projectCreateRequest);

        Mockito.when(sessionUtil.getCurrentUserEmail()).thenReturn(user.getEmail());

        DashboardProjectProjection expectedProject = new DashboardProjectProjection(project.getId(), project.getName(), game.getType());

        DashboardDetailsResponse detailsResponse = dashboardService.getDashboardDetails();

        Assertions.assertEquals(expectedProject, detailsResponse.getProjects().get(0));
    }

    @Test
    public void whenDashboardDetailsHasNonVerifiedUserThenReturnEmailVerifiedFalse() {

        Mockito.when(sessionUtil.getCurrentUserEmail()).thenReturn(user.getEmail());

        DashboardDetailsResponse detailsResponse = dashboardService.getDashboardDetails();

        Assertions.assertFalse(detailsResponse.isEmailVerified());
    }

    @Test
    public void whenDashboardDetailsHasNoProjectsThenReturnHasProjectsFalse() {

        Mockito.when(sessionUtil.getCurrentUserEmail()).thenReturn(user.getEmail());

        DashboardDetailsResponse detailsResponse = dashboardService.getDashboardDetails();

        Assertions.assertFalse(detailsResponse.isHasProjects());
    }

    @Test
    public void whenProjectCreateProjectIsValidThenReturnProjectId() {

        RegionCreateRequest regionCreateRequest = TestRegionUtil.builder(TestRegionUtil.Type.US_EAST_VA_1).build();
        Region region = regionService.handleRegionCreate(regionCreateRequest);

        FlavorCreateRequest flavorCreateRequest = TestFlavorUtil.builder(TestFlavorUtil.Type.S1_2).build();
        Flavor flavor = flavorService.handleFlavorCreate(flavorCreateRequest);

        ImageCreateRequest imageCreateRequest = TestImageUtil.builder(TestImageUtil.Type.UBUNTU_20_4).build();
        Image image = imageService.handleImageCreate(imageCreateRequest);

        CredentialCreateRequest credentialCreateRequest = TestCredential.createDefault();
        Credential credential = credentialService.handleSshKeyCreate(credentialCreateRequest);

        Mockito.when(sessionUtil.getCurrentUser()).thenReturn(user);

        OvhInstanceGroupApiResponse instanceGroupApiResponse = new OvhInstanceGroupApiResponse();
        instanceGroupApiResponse.setId("5eeb0772-7658-46e2-ad95-b2566ccdd394");
        instanceGroupApiResponse.setName("d4afbaa3-0b2a-47a1-9c76-6dd2eab75042");
        instanceGroupApiResponse.setRegion("US-EAST-VA-1");
        instanceGroupApiResponse.setType("affinity");

        Mockito.when(ovhClient.createGroup(Mockito.anyString(), Mockito.any())).thenReturn(instanceGroupApiResponse);

        OvhInstanceApiResponse instanceApiResponse = new OvhInstanceApiResponse();
        instanceApiResponse.setId("b6625973-469d-42b4-8e4e-9ede2b3305bd");
        instanceApiResponse.setName("Test Project Name");
        instanceApiResponse.setStatus(InstanceStatus.BUILD);
        instanceApiResponse.setCreatedDate(LocalDateTime.now());
        instanceApiResponse.setRegion("US-EAST-VA-1");

        OvhSshKeyDetail sshKeyDetail = new OvhSshKeyDetail();
        sshKeyDetail.setId("ssh key id");

        instanceApiResponse.setSshKey(sshKeyDetail);

        OvhFlavorApiResponse flavorApiResponse = new OvhFlavorApiResponse();
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

        OvhImageApiResponse imageApiResponse = new OvhImageApiResponse();
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

        Mockito.when(ovhClient.createInstance(Mockito.anyString(), Mockito.any())).thenReturn(instanceApiResponse);

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
