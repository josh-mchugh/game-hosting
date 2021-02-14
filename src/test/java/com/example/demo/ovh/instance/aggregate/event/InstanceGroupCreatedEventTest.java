package com.example.demo.ovh.instance.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class InstanceGroupCreatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        InstanceGroupCreatedEvent event = InstanceGroupCreatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasProjectIdThenReturnProjectId() {

        UUID projectId = UUID.randomUUID();

        InstanceGroupCreatedEvent event = InstanceGroupCreatedEvent.builder()
                .projectId(projectId)
                .build();

        Assertions.assertEquals(projectId, event.getProjectId());
    }

    @Test
    public void whenEventHasOvhIdThenReturnOvhId() {

        InstanceGroupCreatedEvent event = InstanceGroupCreatedEvent.builder()
                .ovhId("ovhId")
                .build();

        Assertions.assertEquals("ovhId", event.getOvhId());
    }

    @Test
    public void whenEventHasNameThenReturnName() {

        InstanceGroupCreatedEvent event = InstanceGroupCreatedEvent.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", event.getName());
    }

    @Test
    public void whenEventHasTypeThenReturnType() {

        InstanceGroupCreatedEvent event = InstanceGroupCreatedEvent.builder()
                .type("type")
                .build();

        Assertions.assertEquals("type", event.getType());
    }

    @Test
    public void whenEventToString() {

        InstanceGroupCreatedEvent event = event();

        String expected = "InstanceGroupCreatedEvent(id=64a577bf-745b-4f91-9ec2-37f463116b5a, projectId=cb860cba-dc56-4d30-b014-ea90cc37babc, ovhId=ovhId, name=name, type=type)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        InstanceGroupCreatedEvent event = event();

        Assertions.assertEquals(-2139628484, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        InstanceGroupCreatedEvent event1 = event();
        InstanceGroupCreatedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        InstanceGroupCreatedEvent event = event();

        Assertions.assertNotEquals(event, InstanceGroupCreatedEvent.builder().build());
    }

    private InstanceGroupCreatedEvent event() {

        return InstanceGroupCreatedEvent.builder()
                .id(UUID.fromString("64a577bf-745b-4f91-9ec2-37f463116b5a"))
                .projectId(UUID.fromString("cb860cba-dc56-4d30-b014-ea90cc37babc"))
                .ovhId("ovhId")
                .name("name")
                .type("type")
                .build();
    }
}
