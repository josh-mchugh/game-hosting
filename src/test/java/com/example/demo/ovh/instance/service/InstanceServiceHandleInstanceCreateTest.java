package com.example.demo.ovh.instance.service;

import com.example.demo.game.entity.GameType;
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
import com.example.demo.ovh.region.model.Region;
import com.example.demo.ovh.region.service.IRegionService;
import com.example.demo.ovh.region.service.model.RegionCreateRequest;
import com.example.demo.project.model.Project;
import com.example.demo.project.service.IProjectService;
import com.example.demo.project.service.model.ProjectCreateRequest;
import com.example.demo.sample.TestCredential;
import com.example.demo.sample.TestFlavorUtil;
import com.example.demo.sample.TestImageUtil;
import com.example.demo.sample.TestInstanceGroupUtil;
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

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class InstanceServiceHandleInstanceCreateTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IInstanceGroupService instanceGroupService;

    @Autowired
    private IInstanceService instanceService;

    @Autowired
    private IFlavorService flavorService;

    @Autowired
    private IRegionService regionService;

    @Autowired
    private IImageService imageService;

    @Autowired
    private ICredentialService credentialService;

    private final String dependencyIds = "when-handle-instance-create";

    @BeforeEach
    public void setup() {

        Region region = createRegion();
        Flavor flavor = createFlavor();
        Image image = createImage();
        Credential credential = createCredential();
        InstanceGroup instanceGroup = createInstanceGroup();
    }

    @Test
    public void whenHandleInstanceCreateThenNotNull() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-handle-instance-created-then-not-null")
                .imageId(dependencyIds)
                .flavorId(dependencyIds)
                .groupId(dependencyIds)
                .sshKeyId(dependencyIds)
                .status(InstanceStatus.ACTIVE)
                .build();

        Instance instance = instanceService.handleInstanceCreate(instanceCreateRequest);

        Assertions.assertNotNull(instance);
    }

    @Test
    public void whenInstanceCreateIsNullThenThrowException() {

        Exception exception = Assertions.assertThrows(NullPointerException.class, () -> instanceService.handleInstanceCreate(null));

        Assertions.assertNotNull(exception);
    }

    @Test
    public void whenInstanceCreateHasNullImageIdThenThrowException() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-handle-instance-created-then-not-null")
                .flavorId(dependencyIds)
                .status(InstanceStatus.ACTIVE)
                .build();

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> instanceService.handleInstanceCreate(instanceCreateRequest));

        Assertions.assertTrue(exception.getMessage().contains("eq(null) is not allowed. Use isNull() instead"));
    }

    @Test
    public void whenInstanceCreateHasNullFlavorIdThenThrowException() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-handle-instance-created-then-not-null")
                .imageId(dependencyIds)
                .status(InstanceStatus.ACTIVE)
                .build();

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> instanceService.handleInstanceCreate(instanceCreateRequest));

        Assertions.assertTrue(exception.getMessage().contains("eq(null) is not allowed. Use isNull() instead"));
    }

    @Test
    public void whenInstanceCreateHasInstanceIdThenReturnInstanceId() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-has-instance-id-return-instance-id")
                .imageId(dependencyIds)
                .flavorId(dependencyIds)
                .groupId(dependencyIds)
                .sshKeyId(dependencyIds)
                .status(InstanceStatus.ACTIVE)
                .build();

        Instance instance = instanceService.handleInstanceCreate(instanceCreateRequest);

        Assertions.assertEquals("when-has-instance-id-return-instance-id", instance.getInstanceId());
    }

    @Test
    public void whenInstanceCreateHasNullInstanceIdThenThrowException() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .flavorId(dependencyIds)
                .imageId(dependencyIds)
                .groupId(dependencyIds)
                .sshKeyId(dependencyIds)
                .status(InstanceStatus.ACTIVE)
                .build();

        Exception exception = Assertions.assertThrows(PersistenceException.class, () -> instanceService.handleInstanceCreate(instanceCreateRequest));

        Assertions.assertTrue(exception.getMessage().contains("not-null property references a null or transient value : com.example.demo.ovh.instance.entity.InstanceEntity.instanceId"));
    }

    @Test
    public void whenInstanceCreateHasNameThenReturnName() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-instance-create-has-name-return-name")
                .name("name")
                .flavorId(dependencyIds)
                .imageId(dependencyIds)
                .groupId(dependencyIds)
                .sshKeyId(dependencyIds)
                .status(InstanceStatus.ACTIVE)
                .build();

        Instance instance = instanceService.handleInstanceCreate(instanceCreateRequest);

        Assertions.assertEquals("name", instance.getName());
    }

    @Test
    public void whenInstanceCrateHasNullNameThenReturnNull() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-instance-create-has-name-return-null")
                .flavorId(dependencyIds)
                .imageId(dependencyIds)
                .groupId(dependencyIds)
                .sshKeyId(dependencyIds)
                .status(InstanceStatus.ACTIVE)
                .build();

        Instance instance = instanceService.handleInstanceCreate(instanceCreateRequest);

        Assertions.assertNull(instance.getName());
    }

    @Test
    public void whenInstanceCreateHasStatusThenReturnStatus() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-instance-create-has-status-return-status")
                .flavorId(dependencyIds)
                .imageId(dependencyIds)
                .groupId(dependencyIds)
                .sshKeyId(dependencyIds)
                .status(InstanceStatus.ACTIVE)
                .build();

        Instance instance = instanceService.handleInstanceCreate(instanceCreateRequest);

        Assertions.assertEquals(InstanceStatus.ACTIVE, instance.getStatus());
    }

    @Test
    public void whenInstanceCreateHasNullStatusThenThrowException() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-instance-create-status-null-throw-exception")
                .flavorId(dependencyIds)
                .imageId(dependencyIds)
                .groupId(dependencyIds)
                .sshKeyId(dependencyIds)
                .build();

        Exception exception = Assertions.assertThrows(PersistenceException.class, () -> instanceService.handleInstanceCreate(instanceCreateRequest));

        Assertions.assertTrue(exception.getMessage().contains("not-null property references a null or transient value : com.example.demo.ovh.instance.entity.InstanceEntity.status"));
    }

    @Test
    public void whenInstanceCreateHasInstanceCreatedDateThenReturnInstanceCreatedDate() {

        LocalDateTime currentTime = LocalDateTime.now();

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-instance-create-has-instance-created-date-return-instance-created-date")
                .flavorId(dependencyIds)
                .imageId(dependencyIds)
                .groupId(dependencyIds)
                .sshKeyId(dependencyIds)
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(currentTime)
                .build();

        Instance instance = instanceService.handleInstanceCreate(instanceCreateRequest);

        Assertions.assertEquals(currentTime, instance.getInstanceCreatedDate());
    }

    @Test
    public void whenInstanceCreateHasNullInstanceCreatedDateThenReturnNull() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-instance-create-has-null-instance-created-date-return-null")
                .flavorId(dependencyIds)
                .imageId(dependencyIds)
                .groupId(dependencyIds)
                .sshKeyId(dependencyIds)
                .status(InstanceStatus.ACTIVE)
                .build();

        Instance instance = instanceService.handleInstanceCreate(instanceCreateRequest);

        Assertions.assertNull(instance.getInstanceCreatedDate());
    }

    @Test
    public void whenInstanceCreateHasIp4AddressThenReturnIp4Address() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-instance-create-has-ip4-address-return-ip4-address")
                .flavorId(dependencyIds)
                .imageId(dependencyIds)
                .groupId(dependencyIds)
                .sshKeyId(dependencyIds)
                .status(InstanceStatus.ACTIVE)
                .ip4Address("ip4-address")
                .build();

        Instance instance = instanceService.handleInstanceCreate(instanceCreateRequest);

        Assertions.assertEquals("ip4-address", instance.getIp4Address());
    }

    @Test
    public void whenInstanceCreateHasNullIp4AddressThenReturnNull() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-instance-created-has-null-ip4-address-return-null")
                .flavorId(dependencyIds)
                .imageId(dependencyIds)
                .groupId(dependencyIds)
                .sshKeyId(dependencyIds)
                .status(InstanceStatus.ACTIVE)
                .build();

        Instance instance = instanceService.handleInstanceCreate(instanceCreateRequest);

        Assertions.assertNull(instance.getIp4Address());
    }

    @Test
    public void whenInstanceCreateHasIp6AddressThenReturnIp6Address() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-instance-has-ip6-address-then-return-ip6-address")
                .flavorId(dependencyIds)
                .imageId(dependencyIds)
                .groupId(dependencyIds)
                .sshKeyId(dependencyIds)
                .status(InstanceStatus.ACTIVE)
                .ip6Address("ip6-address")
                .build();

        Instance instance = instanceService.handleInstanceCreate(instanceCreateRequest);

        Assertions.assertEquals("ip6-address", instance.getIp6Address());
    }

    @Test
    public void whenInstanceCreateHasNullIp6AddressThenReturnNull() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-instance-has-null-ip6-address-then-return-null")
                .flavorId(dependencyIds)
                .imageId(dependencyIds)
                .groupId(dependencyIds)
                .sshKeyId(dependencyIds)
                .status(InstanceStatus.ACTIVE)
                .build();

        Instance instance = instanceService.handleInstanceCreate(instanceCreateRequest);

        Assertions.assertNull(instance.getIp6Address());
    }

    private Region createRegion() {

        RegionCreateRequest regionCreateRequest = TestRegionUtil.builder()
                .regionName(dependencyIds)
                .build();

        return regionService.handleRegionCreate(regionCreateRequest);
    }

    private Flavor createFlavor() {

        FlavorCreateRequest flavorCreateRequest = TestFlavorUtil.builder()
                .flavorId(dependencyIds)
                .regionName(dependencyIds)
                .build();

        return flavorService.handleFlavorCreate(flavorCreateRequest);
    }

    private Image createImage() {

        ImageCreateRequest imageCreateRequest = TestImageUtil.builder(TestImageUtil.Type.DEBIAN_8_GITLAB)
                .imageId(dependencyIds)
                .regionName(dependencyIds)
                .build();

        return imageService.handleImageCreate(imageCreateRequest);
    }

    private Credential createCredential() {

        CredentialCreateRequest request = TestCredential.builder()
                .sshKeyId(dependencyIds)
                .build();

        return credentialService.handleSshKeyCreate(request);
    }

    private InstanceGroup createInstanceGroup() {

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("test@test");
        User user = userService.handleCreateUser(userCreateRequest);

        ProjectCreateRequest projectCreateRequest = TestProjectUtil.builder()
                .name(dependencyIds)
                .userId(user)
                .gameType(GameType.MINECRAFT_JAVA)
                .build();
        Project project = projectService.handleProjectCreate(projectCreateRequest);

        InstanceGroupCreateRequest instanceGroupCreateRequest = TestInstanceGroupUtil.builder()
                .instanceGroupId(dependencyIds)
                .projectId(project.getId())
                .build();

        return instanceGroupService.handleInstanceGroupCreate(instanceGroupCreateRequest);
    }
}
