package com.example.demo.awx.playbook.aggregate.event;

import com.example.demo.awx.playbook.entity.PlaybookType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxPlaybookCreatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxPlaybookCreatedEvent event = AwxPlaybookCreatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasAwxProjectIdThenReturnAwxProjectId() {

        AwxPlaybookCreatedEvent event = AwxPlaybookCreatedEvent.builder()
                .awxProjectId("awxProjectId")
                .build();

        Assertions.assertEquals("awxProjectId", event.getAwxProjectId());
    }

    @Test
    public void whenEventHasNameThenReturnName() {

        AwxPlaybookCreatedEvent event = AwxPlaybookCreatedEvent.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", event.getName());
    }

    @Test
    public void whenEventHasTypeThenReturnType() {

        AwxPlaybookCreatedEvent event = AwxPlaybookCreatedEvent.builder()
                .type(PlaybookType.TEST)
                .build();

        Assertions.assertEquals(PlaybookType.TEST, event.getType());
    }

    @Test
    public void whenEventToString() {

        AwxPlaybookCreatedEvent event = AwxPlaybookCreatedEvent.builder()
                .id(UUID.fromString("ac4dfb53-a8cb-41ab-9ba7-cb2478130416"))
                .awxProjectId("awxProjectId")
                .name("name")
                .type(PlaybookType.TEST)
                .build();

        String expected = "AwxPlaybookCreatedEvent(id=ac4dfb53-a8cb-41ab-9ba7-cb2478130416, awxProjectId=awxProjectId, name=name, type=TEST)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        AwxPlaybookCreatedEvent event = event();

        Assertions.assertEquals(-1633278875, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        AwxPlaybookCreatedEvent event1 = event();
        AwxPlaybookCreatedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        AwxPlaybookCreatedEvent event = event();

        Assertions.assertNotEquals(event, AwxPlaybookCreatedEvent.builder().build());
    }

    private AwxPlaybookCreatedEvent event() {

        return AwxPlaybookCreatedEvent.builder()
                .id(UUID.fromString("ac4dfb53-a8cb-41ab-9ba7-cb2478130416"))
                .awxProjectId("awxProjectId")
                .name("name")
                .build();
    }
}
