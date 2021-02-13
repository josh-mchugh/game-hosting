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

        UUID flavorId = UUID.randomUUID();

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .flavorId(flavorId)
                .build();

        Assertions.assertEquals(flavorId, event.getFlavorId());
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

        UUID credentialId = UUID.randomUUID();

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .credentialId(credentialId)
                .build();

        Assertions.assertEquals(credentialId, event.getCredentialId());
    }

    @Test
    public void whenEventHasInstanceGroupIdThenReturnInstanceGroupId() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .instanceGroupId("instanceGroupId")
                .build();

        Assertions.assertEquals("instanceGroupId", event.getInstanceGroupId());
    }

    @Test
    public void whenEventHasOvhIdThenReturnOvhId() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .ovhId("ovhId")
                .build();

        Assertions.assertEquals("ovhId", event.getOvhId());
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

        InstanceCreatedEvent event = event();

        String expected = "InstanceCreatedEvent(id=22339936-b02c-40d7-a0d7-52fe4b23a1e2, flavorId=4f4e0a35-287c-454a-9f1f-0deec8d6a941, imageId=imageId, credentialId=ac8707a3-4aba-41d6-a58e-43df396a8c51, instanceGroupId=instanceGroupId, ovhId=ovhId, status=ACTIVE, name=name, instanceCreatedDate=2020-11-28T22:44)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventToHashCode() {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(UUID.fromString("22339936-b02c-40d7-a0d7-52fe4b23a1e2"))
                .flavorId(UUID.fromString("4f4e0a35-287c-454a-9f1f-0deec8d6a941"))
                .imageId("imageId")
                .credentialId(UUID.fromString("ac8707a3-4aba-41d6-a58e-43df396a8c51"))
                .instanceGroupId("instanceGroupId")
                .ovhId("ovhId")
                .name("name")
                .instanceCreatedDate(LocalDateTime.of(2020, 11, 28, 22, 44))
                .build();

        Assertions.assertEquals(-1718485170, event.hashCode());
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
                .flavorId(UUID.fromString("4f4e0a35-287c-454a-9f1f-0deec8d6a941"))
                .imageId("imageId")
                .credentialId(UUID.fromString("ac8707a3-4aba-41d6-a58e-43df396a8c51"))
                .instanceGroupId("instanceGroupId")
                .ovhId("ovhId")
                .status(InstanceStatus.ACTIVE)
                .name("name")
                .instanceCreatedDate(LocalDateTime.of(2020, 11, 28, 22, 44))
                .build();
    }
}
