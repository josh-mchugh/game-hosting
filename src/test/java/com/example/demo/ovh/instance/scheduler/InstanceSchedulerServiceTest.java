package com.example.demo.ovh.instance.scheduler;

import com.example.demo.game.model.Game;
import com.example.demo.game.service.IGameService;
import com.example.demo.game.service.model.GameCreateRequest;
import com.example.demo.ovh.credential.model.Credential;
import com.example.demo.ovh.credential.service.ICredentialService;
import com.example.demo.ovh.credential.service.model.CredentialCreateRequest;
import com.example.demo.ovh.feign.OvhClient;
import com.example.demo.ovh.feign.model.OvhInstanceApiResponse;
import com.example.demo.ovh.feign.model.OvhIpAddressApi;
import com.example.demo.ovh.flavor.model.Flavor;
import com.example.demo.ovh.flavor.service.IFlavorService;
import com.example.demo.ovh.flavor.service.model.FlavorCreateRequest;
import com.example.demo.ovh.image.model.Image;
import com.example.demo.ovh.image.service.IImageService;
import com.example.demo.ovh.image.service.model.ImageCreateRequest;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.ovh.instance.model.Instance;
import com.example.demo.ovh.instance.model.InstanceGroup;
import com.example.demo.ovh.instance.scheduler.service.IInstanceSchedulerService;
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
import com.example.demo.sample.TestCredential;
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
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
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
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class InstanceSchedulerServiceTest {

    @Autowired
    private IInstanceSchedulerService instanceSchedulerService;

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

        CredentialCreateRequest credentialCreateRequest = TestCredential.createDefault();
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
    public void whenApiResponsesIsEmptyThenReturnEmptyArray() {

        Mockito.when(ovhClient.getInstances(Mockito.anyString())).thenReturn(new ArrayList<>());

        ImmutableList<Instance> updatedInstances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals(0, updatedInstances.size());
    }

    @Test
    public void whenApiResponseIsEqualThenReturnEmptyArray() {

        OvhInstanceApiResponse apiResponse = new OvhInstanceApiResponse();
        apiResponse.setId(instance.getInstanceId());
        apiResponse.setName(instance.getName());
        apiResponse.setStatus(instance.getStatus());
        apiResponse.setIpAddresses(buildIpAddresses());
        apiResponse.setCreatedDate(instance.getInstanceCreatedDate());

        Mockito.when(ovhClient.getInstances(Mockito.anyString())).thenReturn(Lists.newArrayList(apiResponse));

        ImmutableList<Instance> updatedInstances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals(0, updatedInstances.size());

    }

    @Test
    public void whenApiResponseInstanceIdIsValidThenEqualUpdatedInstanceId() {

        OvhInstanceApiResponse apiResponse = new OvhInstanceApiResponse();
        apiResponse.setId(instance.getInstanceId());

        Mockito.when(ovhClient.getInstances(Mockito.anyString())).thenReturn(Lists.newArrayList(apiResponse));

        ImmutableList<Instance> instances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals(apiResponse.getId(), instances.get(0).getInstanceId());
    }

    @Test
    public void whenApiResponseNameIsDifferentThenReturnUpdatedName() {

        OvhInstanceApiResponse apiResponse = new OvhInstanceApiResponse();
        apiResponse.setId(instance.getInstanceId());
        apiResponse.setName("new-name");
        apiResponse.setStatus(instance.getStatus());
        apiResponse.setIpAddresses(buildIpAddresses());
        apiResponse.setCreatedDate(instance.getInstanceCreatedDate());

        Mockito.when(ovhClient.getInstances(Mockito.anyString())).thenReturn(Lists.newArrayList(apiResponse));

        ImmutableList<Instance> updatedInstances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals("new-name", updatedInstances.get(0).getName());
    }

    @Test
    public void whenApiResponseStatusIsDifferentThenReturnUpdatedStatus() {

        OvhInstanceApiResponse apiResponse = new OvhInstanceApiResponse();
        apiResponse.setId(instance.getInstanceId());
        apiResponse.setName(instance.getName());
        apiResponse.setStatus(InstanceStatus.STOPPED);
        apiResponse.setIpAddresses(buildIpAddresses());
        apiResponse.setCreatedDate(instance.getInstanceCreatedDate());

        Mockito.when(ovhClient.getInstances(Mockito.anyString())).thenReturn(Lists.newArrayList(apiResponse));

        ImmutableList<Instance> updatedInstances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals(InstanceStatus.STOPPED, updatedInstances.get(0).getStatus());
    }

    @Test
    public void whenApiResponseCreatedDateIdDifferentThenReturnUpdatedCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        OvhInstanceApiResponse apiResponse = new OvhInstanceApiResponse();
        apiResponse.setId(instance.getInstanceId());
        apiResponse.setName(instance.getName());
        apiResponse.setStatus(instance.getStatus());
        apiResponse.setIpAddresses(buildIpAddresses());
        apiResponse.setCreatedDate(createdDate);

        Mockito.when(ovhClient.getInstances(Mockito.anyString())).thenReturn(Lists.newArrayList(apiResponse));

        ImmutableList<Instance> updatedInstances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals(createdDate, updatedInstances.get(0).getInstanceCreatedDate());
    }

    @Test
    public void whenApiResponseIp4AddressIdDifferentThenReturnUpdatedIp4Address() {

        OvhInstanceApiResponse apiResponse = new OvhInstanceApiResponse();
        apiResponse.setId(instance.getInstanceId());
        apiResponse.setName(instance.getName());
        apiResponse.setStatus(instance.getStatus());
        apiResponse.setIpAddresses(buildIpAddresses("1.1.1.1.1", "0.0.0.0.0.0"));
        apiResponse.setCreatedDate(instance.getInstanceCreatedDate());

        Mockito.when(ovhClient.getInstances(Mockito.anyString())).thenReturn(Lists.newArrayList(apiResponse));

        ImmutableList<Instance> updatedInstances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals("1.1.1.1.1", updatedInstances.get(0).getIp4Address());
    }

    @Test
    public void whenApiResponseIp6AddressIdDifferentThenReturnUpdatedIp6Address() {

        OvhInstanceApiResponse apiResponse = new OvhInstanceApiResponse();
        apiResponse.setId(instance.getInstanceId());
        apiResponse.setName(instance.getName());
        apiResponse.setStatus(instance.getStatus());
        apiResponse.setIpAddresses(buildIpAddresses("0.0.0.0.0.0", "1.1.1.1.1"));
        apiResponse.setCreatedDate(instance.getInstanceCreatedDate());

        Mockito.when(ovhClient.getInstances(Mockito.anyString())).thenReturn(Lists.newArrayList(apiResponse));

        ImmutableList<Instance> updatedInstances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals("1.1.1.1.1", updatedInstances.get(0).getIp6Address());
    }

    private List<OvhIpAddressApi> buildIpAddresses() {

        return buildIpAddresses("0.0.0.0.0.0", "0.0.0.0.0.0");
    }

    private List<OvhIpAddressApi> buildIpAddresses(String ip4Address, String ip6Address) {

        OvhIpAddressApi ip4 = new OvhIpAddressApi();
        ip4.setVersion(4);
        ip4.setIp(ip4Address);

        OvhIpAddressApi ip6 = new OvhIpAddressApi();
        ip6.setVersion(6);
        ip6.setIp(ip6Address);

        return Lists.newArrayList(ip4, ip6);
    }
}
