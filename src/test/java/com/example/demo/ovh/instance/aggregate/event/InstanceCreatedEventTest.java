package com.example.demo.ovh.instance.aggregate.event;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class InstanceCreatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasFlavorIdThenReturnFlavorId() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .flavorId("flavorId")
                .build();

        Assertions.assertEquals("flavorId", event.getFlavorId());
    }

    @Test
    public void whenEventHasImageIdThenReturnImageId() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .imageId("imageId")
                .build();

        Assertions.assertEquals("imageId", event.getImageId());
    }

    @Test
    public void whenEventHasCredentialIdThenReturnCredentialId() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .credentialId("credentialId")
                .build();

        Assertions.assertEquals("credentialId", event.getCredentialId());
    }

    @Test
    public void whenEventHasInstanceGroupIdThenReturnInstanceGroupId() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .instanceGroupId("instanceGroupId")
                .build();

        Assertions.assertEquals("instanceGroupId", event.getInstanceGroupId());
    }

    @Test
    public void whenEventHasInstanceIdThenReturnInstanceId() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .instanceId("instanceId")
                .build();

        Assertions.assertEquals("instanceId", event.getInstanceId());
    }

    @Test
    public void whenEventHasStatusThenReturnStatus() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .status(InstanceStatus.ACTIVE)
                .build();

        Assertions.assertEquals(InstanceStatus.ACTIVE, event.getStatus());
    }

    @Test
    public void whenEventHasNameThenReturnName() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", event.getName());
    }

    @Test
    public void whenEventHasInstanceCreatedDateThenReturnInstanceCreatedDate() {

        LocalDateTime instanceCreatedDate = LocalDateTime.now();

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .instanceCreatedDate(instanceCreatedDate)
                .build();

        Assertions.assertEquals(instanceCreatedDate, event.getInstanceCreatedDate());
    }

    @Test
    public void whenEventToString() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(UUID.fromString("22339936-b02c-40d7-a0d7-52fe4b23a1e2"))
                .flavorId("flavorId")
                .imageId("imageId")
                .credentialId("credentialId")
                .instanceGroupId("instanceGroupId")
                .instanceId("instanceId")
                .status(InstanceStatus.ACTIVE)
                .name("name")
                .instanceCreatedDate(LocalDateTime.of(2020, 11, 28, 22, 44))
                .build();

        String expected = "InstanceCreatedEvent(id=22339936-b02c-40d7-a0d7-52fe4b23a1e2, flavorId=flavorId, imageId=imageId, credentialId=credentialId, instanceGroupId=instanceGroupId, instanceId=instanceId, status=ACTIVE, name=name, instanceCreatedDate=2020-11-28T22:44)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventToHashCode() {

        InstanceCreatedEvent event = event();

        Assertions.assertEquals(1691287762, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        InstanceCreatedEvent event1 = event();
        InstanceCreatedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        InstanceCreatedEvent event = event();

        Assertions.assertNotEquals(event, InstanceCreatedEvent.builder().build());
    }

    private InstanceCreatedEvent event() {

        return InstanceCreatedEvent.builder()
                .id(UUID.fromString("22339936-b02c-40d7-a0d7-52fe4b23a1e2"))
                .flavorId("flavorId")
                .imageId("imageId")
                .credentialId("credentialId")
                .instanceGroupId("instanceGroupId")
                .instanceId("instanceId")
                .name("name")
                .instanceCreatedDate(LocalDateTime.of(2020, 11, 28, 22, 44))
                .build();
    }
}