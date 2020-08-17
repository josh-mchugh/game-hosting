package com.example.demo.ovh.instance.service;

import com.example.demo.game.model.Game;
import com.example.demo.game.service.IGameService;
import com.example.demo.game.service.model.GameCreateRequest;
import com.example.demo.ovh.credential.model.Credential;
import com.example.demo.ovh.credential.service.ICredentialService;
import com.example.demo.ovh.credential.service.model.CredentialCreateRequest;
import com.example.demo.ovh.flavor.model.Flavor;
import com.example.demo.ovh.flavor.service.IFlavorService;
import com.example.demo.ovh.flavor.service.model.FlavorCreateRequest;
import com.example.demo.ovh.image.model.Image;
import com.example.demo.ovh.image.service.IImageService;
import com.example.demo.ovh.image.service.model.ImageCreateRequest;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.ovh.instance.model.Instance;
import com.example.demo.ovh.instance.model.InstanceGroup;
import com.example.demo.ovh.instance.service.model.InstanceCreateRequest;
import com.example.demo.ovh.instance.service.model.InstanceGroupCreateRequest;
import com.example.demo.ovh.instance.service.model.InstanceUpdateRequest;
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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class InstanceServiceHandleUpdateTest {

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
        Project project = projectService.handleProjectCreate(projectCreateRequest);

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
    public void whenUpdateRequestIsNullThenThrowException() {

        Exception exception = Assertions.assertThrows(NullPointerException.class, () -> instanceService.handleInstanceUpdate(null));

        Assertions.assertNotNull(exception);
    }

    @Test
    public void whenUpdateIdIsInvalidThenThrow() {

        InstanceUpdateRequest request = getInstanceUpdateBuilder()
                .id("invalid-id")
                .build();

        Exception exception = Assertions.assertThrows(NullPointerException.class, () -> instanceService.handleInstanceUpdate(request));

        Assertions.assertNotNull(exception);
    }

    @Test
    public void whenUpdateNameIsChangedThenReturnUpdatedName() {

        InstanceUpdateRequest request = getInstanceUpdateBuilder()
                .name("new-name")
                .build();

        Instance instance = instanceService.handleInstanceUpdate(request);

        Assertions.assertEquals("new-name", instance.getName());
    }

    @Test
    public void whenUpdateStatusIsChangedThenReturnUpdatedStatus() {

        InstanceUpdateRequest request = getInstanceUpdateBuilder()
                .status(InstanceStatus.STOPPED)
                .build();

        Instance instance = instanceService.handleInstanceUpdate(request);

        Assertions.assertEquals(InstanceStatus.STOPPED, instance.getStatus());
    }

    @Test
    public void whenUpdateCreateDateThenReturnUpdatedCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        InstanceUpdateRequest request = getInstanceUpdateBuilder()
                .instanceCreatedDate(createdDate)
                .build();

        Instance instance = instanceService.handleInstanceUpdate(request);

        Assertions.assertEquals(createdDate, instance.getInstanceCreatedDate());
    }

    @Test
    public void whenUpdateIp4AddressThenReturnUpdatedIp4Address() {

        InstanceUpdateRequest request = getInstanceUpdateBuilder()
                .ip4Address("1.1.1.1.1.1")
                .build();

        Instance instance = instanceService.handleInstanceUpdate(request);

        Assertions.assertEquals("1.1.1.1.1.1", instance.getIp4Address());
    }

    @Test
    public void whenUpdateIp6AddressThenReturnUpdatedIp6Address() {

        InstanceUpdateRequest request = getInstanceUpdateBuilder()
                .ip4Address("1.1.1.1.1.1")
                .build();

        Instance instance = instanceService.handleInstanceUpdate(request);

        Assertions.assertEquals("1.1.1.1.1.1", instance.getIp4Address());
    }

    @Test
    public void whenUpdateNameIsNullThenReturnNull() {

        InstanceUpdateRequest request = getInstanceUpdateBuilder()
                .name(null)
                .build();

        Instance instance = instanceService.handleInstanceUpdate(request);

        Assertions.assertNull( instance.getName());
    }

    @Test
    public void whenUpdateStatusIsNullThenReturnNull() {

        InstanceUpdateRequest request = getInstanceUpdateBuilder()
                .status(null)
                .build();

        Instance instance = instanceService.handleInstanceUpdate(request);

        Assertions.assertNull(instance.getStatus());
    }

    @Test
    public void whenUpdateCreateDateIsNullThenReturnNull() {

        InstanceUpdateRequest request = getInstanceUpdateBuilder()
                .instanceCreatedDate(null)
                .build();

        Instance instance = instanceService.handleInstanceUpdate(request);

        Assertions.assertNull(instance.getInstanceCreatedDate());
    }

    @Test
    public void whenUpdateIp4AddressIsNullThenReturnNull() {

        InstanceUpdateRequest request = getInstanceUpdateBuilder()
                .ip4Address(null)
                .build();

        Instance instance = instanceService.handleInstanceUpdate(request);

        Assertions.assertNull( instance.getIp4Address());
    }

    @Test
    public void whenUpdateIp6AddressIsNullThenReturnNull() {

        InstanceUpdateRequest request = getInstanceUpdateBuilder()
                .ip4Address(null)
                .build();

        Instance instance = instanceService.handleInstanceUpdate(request);

        Assertions.assertNull(instance.getIp4Address());
    }

    private InstanceUpdateRequest.Builder getInstanceUpdateBuilder() {

        return InstanceUpdateRequest.builder()
                .id(instance.getId())
                .name(instance.getName())
                .status(instance.getStatus())
                .instanceCreatedDate(instance.getInstanceCreatedDate())
                .ip4Address(instance.getIp4Address())
                .ip6Address(instance.getIp6Address());
    }
}
