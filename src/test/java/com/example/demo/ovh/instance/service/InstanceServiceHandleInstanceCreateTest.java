package com.example.demo.ovh.instance.service;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.ovh.instance.model.Instance;
import com.example.demo.ovh.instance.service.model.InstanceCreateRequest;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
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
    private IInstanceService instanceService;

    @Autowired
    private SampleBuilder sampleBuilder;

    private SampleData data;

    @BeforeEach
    public void setup() {

        data = sampleBuilder.builder()
                .user()
                .region()
                .flavor()
                .image()
                .credential()
                .project()
                .instanceGroup()
                .build();
    }

    @Test
    public void whenHandleInstanceCreateThenNotNull() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-handle-instance-created-then-not-null")
                .imageId(data.getImage().getImageId())
                .flavorId(data.getFlavor().getFlavorId())
                .groupId(data.getInstanceGroup().getGroupId())
                .sshKeyId(data.getCredential().getSshKeyId())
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
                .flavorId(data.getFlavor().getFlavorId())
                .status(InstanceStatus.ACTIVE)
                .build();

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> instanceService.handleInstanceCreate(instanceCreateRequest));

        Assertions.assertTrue(exception.getMessage().contains("eq(null) is not allowed. Use isNull() instead"));
    }

    @Test
    public void whenInstanceCreateHasNullFlavorIdThenThrowException() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-handle-instance-created-then-not-null")
                .imageId(data.getImage().getImageId())
                .status(InstanceStatus.ACTIVE)
                .build();

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> instanceService.handleInstanceCreate(instanceCreateRequest));

        Assertions.assertTrue(exception.getMessage().contains("eq(null) is not allowed. Use isNull() instead"));
    }

    @Test
    public void whenInstanceCreateHasInstanceIdThenReturnInstanceId() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-has-instance-id-return-instance-id")
                .imageId(data.getImage().getImageId())
                .flavorId(data.getFlavor().getFlavorId())
                .groupId(data.getInstanceGroup().getGroupId())
                .sshKeyId(data.getCredential().getSshKeyId())
                .status(InstanceStatus.ACTIVE)
                .build();

        Instance instance = instanceService.handleInstanceCreate(instanceCreateRequest);

        Assertions.assertEquals("when-has-instance-id-return-instance-id", instance.getInstanceId());
    }

    @Test
    public void whenInstanceCreateHasNullInstanceIdThenThrowException() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .imageId(data.getImage().getImageId())
                .flavorId(data.getFlavor().getFlavorId())
                .groupId(data.getInstanceGroup().getGroupId())
                .sshKeyId(data.getCredential().getSshKeyId())
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
                .imageId(data.getImage().getImageId())
                .flavorId(data.getFlavor().getFlavorId())
                .groupId(data.getInstanceGroup().getGroupId())
                .sshKeyId(data.getCredential().getSshKeyId())
                .status(InstanceStatus.ACTIVE)
                .build();

        Instance instance = instanceService.handleInstanceCreate(instanceCreateRequest);

        Assertions.assertEquals("name", instance.getName());
    }

    @Test
    public void whenInstanceCrateHasNullNameThenReturnNull() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-instance-create-has-name-return-null")
                .imageId(data.getImage().getImageId())
                .flavorId(data.getFlavor().getFlavorId())
                .groupId(data.getInstanceGroup().getGroupId())
                .sshKeyId(data.getCredential().getSshKeyId())
                .status(InstanceStatus.ACTIVE)
                .build();

        Instance instance = instanceService.handleInstanceCreate(instanceCreateRequest);

        Assertions.assertNull(instance.getName());
    }

    @Test
    public void whenInstanceCreateHasStatusThenReturnStatus() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-instance-create-has-status-return-status")
                .imageId(data.getImage().getImageId())
                .flavorId(data.getFlavor().getFlavorId())
                .groupId(data.getInstanceGroup().getGroupId())
                .sshKeyId(data.getCredential().getSshKeyId())
                .status(InstanceStatus.ACTIVE)
                .build();

        Instance instance = instanceService.handleInstanceCreate(instanceCreateRequest);

        Assertions.assertEquals(InstanceStatus.ACTIVE, instance.getStatus());
    }

    @Test
    public void whenInstanceCreateHasNullStatusThenThrowException() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-instance-create-status-null-throw-exception")
                .imageId(data.getImage().getImageId())
                .flavorId(data.getFlavor().getFlavorId())
                .groupId(data.getInstanceGroup().getGroupId())
                .sshKeyId(data.getCredential().getSshKeyId())
                .build();

        Exception exception = Assertions.assertThrows(PersistenceException.class, () -> instanceService.handleInstanceCreate(instanceCreateRequest));

        Assertions.assertTrue(exception.getMessage().contains("not-null property references a null or transient value : com.example.demo.ovh.instance.entity.InstanceEntity.status"));
    }

    @Test
    public void whenInstanceCreateHasInstanceCreatedDateThenReturnInstanceCreatedDate() {

        LocalDateTime currentTime = LocalDateTime.now();

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-instance-create-has-instance-created-date-return-instance-created-date")
                .imageId(data.getImage().getImageId())
                .flavorId(data.getFlavor().getFlavorId())
                .groupId(data.getInstanceGroup().getGroupId())
                .sshKeyId(data.getCredential().getSshKeyId())
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
                .imageId(data.getImage().getImageId())
                .flavorId(data.getFlavor().getFlavorId())
                .groupId(data.getInstanceGroup().getGroupId())
                .sshKeyId(data.getCredential().getSshKeyId())
                .status(InstanceStatus.ACTIVE)
                .build();

        Instance instance = instanceService.handleInstanceCreate(instanceCreateRequest);

        Assertions.assertNull(instance.getInstanceCreatedDate());
    }

    @Test
    public void whenInstanceCreateHasIp4AddressThenReturnIp4Address() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-instance-create-has-ip4-address-return-ip4-address")
                .imageId(data.getImage().getImageId())
                .flavorId(data.getFlavor().getFlavorId())
                .groupId(data.getInstanceGroup().getGroupId())
                .sshKeyId(data.getCredential().getSshKeyId())
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
                .imageId(data.getImage().getImageId())
                .flavorId(data.getFlavor().getFlavorId())
                .groupId(data.getInstanceGroup().getGroupId())
                .sshKeyId(data.getCredential().getSshKeyId())
                .status(InstanceStatus.ACTIVE)
                .build();

        Instance instance = instanceService.handleInstanceCreate(instanceCreateRequest);

        Assertions.assertNull(instance.getIp4Address());
    }

    @Test
    public void whenInstanceCreateHasIp6AddressThenReturnIp6Address() {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId("when-instance-has-ip6-address-then-return-ip6-address")
                .imageId(data.getImage().getImageId())
                .flavorId(data.getFlavor().getFlavorId())
                .groupId(data.getInstanceGroup().getGroupId())
                .sshKeyId(data.getCredential().getSshKeyId())
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
                .imageId(data.getImage().getImageId())
                .flavorId(data.getFlavor().getFlavorId())
                .groupId(data.getInstanceGroup().getGroupId())
                .sshKeyId(data.getCredential().getSshKeyId())
                .status(InstanceStatus.ACTIVE)
                .build();

        Instance instance = instanceService.handleInstanceCreate(instanceCreateRequest);

        Assertions.assertNull(instance.getIp6Address());
    }
}
