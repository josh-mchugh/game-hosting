package com.example.demo.web.project.service;

import com.example.demo.game.model.Game;
import com.example.demo.game.service.IGameService;
import com.example.demo.game.service.model.GameCreateRequest;
import com.example.demo.ovh.credential.model.Credential;
import com.example.demo.ovh.credential.service.ICredentialService;
import com.example.demo.ovh.credential.service.model.CredentialCreateRequest;
import com.example.demo.ovh.feign.OvhClient;
import com.example.demo.ovh.flavor.model.Flavor;
import com.example.demo.ovh.flavor.service.IFlavorService;
import com.example.demo.ovh.flavor.service.model.FlavorCreateRequest;
import com.example.demo.ovh.image.model.Image;
import com.example.demo.ovh.image.service.IImageService;
import com.example.demo.ovh.image.service.model.ImageCreateRequest;
import com.example.demo.ovh.instance.model.Instance;
import com.example.demo.ovh.instance.model.InstanceGroup;
import com.example.demo.ovh.instance.service.IInstanceGroupService;
import com.example.demo.ovh.instance.service.IInstanceService;
import com.example.demo.ovh.instance.service.model.InstanceCreateRequest;
import com.example.demo.ovh.instance.service.model.InstanceGroupCreateRequest;
import com.example.demo.ovh.region.model.Region;
import com.example.demo.ovh.region.service.IRegionService;
import com.example.demo.ovh.region.service.model.RegionCreateRequest;
import com.example.demo.project.model.Project;
import com.example.demo.project.service.IProjectService;
import com.example.demo.project.service.model.ProjectCreateRequest;
import com.example.demo.sample.TestCredentialUtil;
import com.example.demo.sample.TestFlavorUtil;
import com.example.demo.sample.TestGameUtil;
import com.example.demo.sample.TestImageUtil;
import com.example.demo.sample.TestInstanceGroupUtil;
import com.example.demo.sample.TestInstanceUtil;
import com.example.demo.sample.TestProjectUtil;
import com.example.demo.sample.TestRegionUtil;
import com.example.demo.sample.TestUserUtil;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserCreateRequest;
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
    private IUserService userService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IGameService gameService;

    @Autowired
    private IInstanceGroupService instanceGroupService;

    @Autowired
    private IInstanceService instanceService;

    @Autowired
    private IFlavorService flavorService;

    @Autowired
    private IImageService imageService;

    @Autowired
    private IRegionService regionService;

    @Autowired
    private ICredentialService credentialService;

    @MockBean
    private OvhClient ovhClient;

    private Project project;
    private Instance instance;

    @BeforeEach
    public void setup() {

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("test@test");
        User user = userService.handleCreateUser(userCreateRequest);

        GameCreateRequest gameCreateRequest = TestGameUtil.builder().build();
        Game game = gameService.handleGameCreateRequest(gameCreateRequest);

        RegionCreateRequest regionCreateRequest = TestRegionUtil.builder().build();
        Region region = regionService.handleRegionCreate(regionCreateRequest);

        FlavorCreateRequest flavorCreateRequest = TestFlavorUtil.builder().build();
        Flavor flavor = flavorService.handleFlavorCreate(flavorCreateRequest);

        ImageCreateRequest imageCreateRequest = TestImageUtil.builder(TestImageUtil.Type.UBUNTU_20_4).build();
        Image image = imageService.handleImageCreate(imageCreateRequest);

        CredentialCreateRequest credentialCreateRequest = TestCredentialUtil.createDefault();
        Credential credential = credentialService.handleSshKeyCreate(credentialCreateRequest);

        ProjectCreateRequest projectCreateRequest = TestProjectUtil.builder()
                .userId(user)
                .gameType(game)
                .name("project-name")
                .build();
        project = projectService.handleProjectCreate(projectCreateRequest);

        InstanceGroupCreateRequest instanceGroupCreateRequest = TestInstanceGroupUtil.builder()
                .projectId(project.getId())
                .instanceGroupId("instance-group-id")
                .name("instance-group-name")
                .build();
        InstanceGroup instanceGroup = instanceGroupService.handleInstanceGroupCreate(instanceGroupCreateRequest);

        InstanceCreateRequest instanceCreateRequest = TestInstanceUtil.builder()
                .instanceId("instance-id")
                .groupId(instanceGroup.getGroupId())
                .name("instance-name")
                .build();
        instance = instanceService.handleInstanceCreate(instanceCreateRequest);
    }

    @Test
    public void whenGetProjectDetailsHasValidIdThenReturnProjectDetails() {

        ProjectDetails projectDetails = projectControllerService.getProjectDetails(project.getId());

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

        Mockito.doNothing().when(ovhClient).startInstance(project.getId(), instance.getInstanceId());

        ProjectInstanceStartRequest request = ProjectInstanceStartRequest.builder()
                .projectId(project.getId())
                .instanceId(instance.getInstanceId())
                .build();

        Assertions.assertDoesNotThrow(() -> projectControllerService.handleProjectInstanceStart(request));
    }

    @Test
    public void whenHandleProjectInstanceStartHasValidIdThenThrowException() {

        Mockito.doThrow(FeignException.FeignClientException.class).when(ovhClient).startInstance(Mockito.anyString(), Mockito.anyString());

        ProjectInstanceStartRequest request = ProjectInstanceStartRequest.builder()
                .projectId("asdf")
                .instanceId("asdf")
                .build();

        Assertions.assertThrows(FeignException.FeignClientException.class, () -> projectControllerService.handleProjectInstanceStart(request));
    }

    @Test
    public void whenHandleProjectInstanceStopHasValidIdThenThrowNoException() {

        Mockito.doNothing().when(ovhClient).startInstance(project.getId(), instance.getInstanceId());

        ProjectInstanceStopRequest request = ProjectInstanceStopRequest.builder()
                .projectId(project.getId())
                .instanceId(instance.getInstanceId())
                .build();

        Assertions.assertDoesNotThrow(() -> projectControllerService.handleProjectInstanceStop(request));
    }

    @Test
    public void whenHandleProjectInstanceStopHasValidIdThenThrowException() {

        Mockito.doThrow(FeignException.FeignClientException.class).when(ovhClient).stopInstance(Mockito.anyString(), Mockito.anyString());

        ProjectInstanceStopRequest request = ProjectInstanceStopRequest.builder()
                .projectId("asdf")
                .instanceId("asdf")
                .build();

        Assertions.assertThrows(FeignException.FeignClientException.class, () -> projectControllerService.handleProjectInstanceStop(request));
    }
}
