package com.example.demo.ovh.instance.entity.service;

import com.example.demo.ovh.instance.aggregate.event.InstanceCreatedEvent;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.ovh.instance.entity.model.Instance;
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
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class InstanceServiceCreatedTest {

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
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> instanceService.handleCreated(null));
    }

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(id)
                .flavorId(data.getFlavor().getId())
                .imageId(data.getImage().getId())
                .instanceGroupId(data.getInstanceGroup().getId())
                .credentialId(data.getCredential().getId())
                .ovhId("ovhId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        Instance instance = instanceService.handleCreated(event);

        Assertions.assertEquals(id.toString(), instance.getId());
    }

    @Test
    public void whenEventHasNullIdThenExpectException() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(null)
                .flavorId(data.getFlavor().getId())
                .imageId(data.getImage().getId())
                .instanceGroupId(data.getInstanceGroup().getId())
                .credentialId(data.getCredential().getId())
                .ovhId("ovhId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> instanceService.handleCreated(event));
    }

    @Test
    public void whenEventHasNullFlavorIdThenExpectException() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(UUID.randomUUID())
                .flavorId(null)
                .imageId(data.getImage().getId())
                .instanceGroupId(data.getInstanceGroup().getId())
                .credentialId(data.getCredential().getId())
                .ovhId("ovhId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> instanceService.handleCreated(event));
    }

    @Test
    public void whenEventHasInvalidFlavorIdThenExpectException() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(UUID.randomUUID())
                .flavorId(UUID.randomUUID())
                .imageId(data.getImage().getId())
                .instanceGroupId(data.getInstanceGroup().getId())
                .credentialId(data.getCredential().getId())
                .ovhId("ovhId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> instanceService.handleCreated(event));
    }

    @Test
    public void whenEventHasNullImageIdThenExpectException() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(UUID.randomUUID())
                .flavorId(data.getFlavor().getId())
                .imageId(null)
                .instanceGroupId(data.getInstanceGroup().getId())
                .credentialId(data.getCredential().getId())
                .ovhId("ovhId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> instanceService.handleCreated(event));
    }

    @Test
    public void whenEventHasInvalidImageIdThenExpectException() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(UUID.randomUUID())
                .flavorId(data.getFlavor().getId())
                .imageId(UUID.randomUUID())
                .instanceGroupId(data.getInstanceGroup().getId())
                .credentialId(data.getCredential().getId())
                .ovhId("ovhId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> instanceService.handleCreated(event));
    }

    @Test
    public void whenEventHasNullInstanceGroupIdThenExpectException() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(UUID.randomUUID())
                .flavorId(data.getFlavor().getId())
                .imageId(data.getImage().getId())
                .instanceGroupId(null)
                .credentialId(data.getCredential().getId())
                .ovhId("ovhId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> instanceService.handleCreated(event));
    }

    @Test
    public void whenEventHasInvalidInstanceGroupIdThenExpectException() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(UUID.randomUUID())
                .flavorId(data.getFlavor().getId())
                .imageId(data.getImage().getId())
                .instanceGroupId("invalidId")
                .credentialId(data.getCredential().getId())
                .ovhId("ovhId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> instanceService.handleCreated(event));
    }

    @Test
    public void whenEventHasNullCredentialIdThenExpectException() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(UUID.randomUUID())
                .flavorId(data.getFlavor().getId())
                .imageId(data.getImage().getId())
                .instanceGroupId(data.getInstanceGroup().getId())
                .credentialId(null)
                .ovhId("ovhId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> instanceService.handleCreated(event));
    }

    @Test
    public void whenEventHasInvalidCredentialIdThenExpectException() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(UUID.randomUUID())
                .flavorId(data.getFlavor().getId())
                .imageId(data.getImage().getId())
                .instanceGroupId(data.getInstanceGroup().getId())
                .credentialId(UUID.randomUUID())
                .ovhId("ovhId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> instanceService.handleCreated(event));
    }

    @Test
    public void whenEventHasOvhIdThenReturnOvhId() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(UUID.randomUUID())
                .flavorId(data.getFlavor().getId())
                .imageId(data.getImage().getId())
                .instanceGroupId(data.getInstanceGroup().getId())
                .credentialId(data.getCredential().getId())
                .ovhId("ovhId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        Instance instance = instanceService.handleCreated(event);

        Assertions.assertEquals("ovhId", instance.getOvhId());
    }

    @Test
    public void whenEventHasNullOvhIdThenExpectException() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(UUID.randomUUID())
                .flavorId(data.getFlavor().getId())
                .imageId(data.getImage().getId())
                .instanceGroupId(data.getInstanceGroup().getId())
                .credentialId(data.getCredential().getId())
                .ovhId(null)
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> instanceService.handleCreated(event));
    }

    @Test
    public void whenEventHasNameThenReturnName() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(UUID.randomUUID())
                .flavorId(data.getFlavor().getId())
                .imageId(data.getImage().getId())
                .instanceGroupId(data.getInstanceGroup().getId())
                .credentialId(data.getCredential().getId())
                .ovhId("ovhId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        Instance instance = instanceService.handleCreated(event);

        Assertions.assertEquals("name", instance.getName());
    }


    @Test
    public void whenEventHasStatusThenReturnStatus() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(UUID.randomUUID())
                .flavorId(data.getFlavor().getId())
                .imageId(data.getImage().getId())
                .instanceGroupId(data.getInstanceGroup().getId())
                .credentialId(data.getCredential().getId())
                .ovhId("ovhId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        Instance instance = instanceService.handleCreated(event);

        Assertions.assertEquals(InstanceStatus.ACTIVE, instance.getStatus());
    }

    @Test
    public void whenEventHasNullStatusThenExpectException() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(UUID.randomUUID())
                .flavorId(data.getFlavor().getId())
                .imageId(data.getImage().getId())
                .instanceGroupId(data.getInstanceGroup().getId())
                .credentialId(data.getCredential().getId())
                .ovhId("ovhId")
                .name("name")
                .status(null)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> instanceService.handleCreated(event));
    }

    @Test
    public void whenInstanceCreateHasInstanceCreatedDateThenReturnInstanceCreatedDate() {

        LocalDateTime instanceCreatedDate = LocalDateTime.now();

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(UUID.randomUUID())
                .flavorId(data.getFlavor().getId())
                .imageId(data.getImage().getId())
                .instanceGroupId(data.getInstanceGroup().getId())
                .credentialId(data.getCredential().getId())
                .ovhId("ovhId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(instanceCreatedDate)
                .build();

        Instance instance = instanceService.handleCreated(event);

        Assertions.assertEquals(instanceCreatedDate, instance.getInstanceCreatedDate());
    }
}
