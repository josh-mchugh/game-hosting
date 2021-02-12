package com.example.demo.awx.host.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxHostCreatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasAwxInventoryIdThenReturnAwxInventoryId() {

        UUID awxInventoryId = UUID.randomUUID();

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .awxInventoryId(awxInventoryId)
                .build();

        Assertions.assertEquals(awxInventoryId, event.getAwxInventoryId());
    }

    @Test
    public void whenEventHasInstanceIdThenReturnInstanceId() {

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .instanceId("instanceId")
                .build();

        Assertions.assertEquals("instanceId", event.getInstanceId());
    }

    @Test
    public void whenEventHasAwxIdThenReturnAwxId() {

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, event.getAwxId());
    }

    @Test
    public void whenEventHasHostnameThenReturnHostname() {

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .hostname("hostname")
                .build();

        Assertions.assertEquals("hostname", event.getHostname());
    }

    @Test
    public void whenEventHasDescriptionThenReturnDescription() {

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", event.getDescription());
    }

    @Test
    public void whenEventHasEnabledThenReturnEnabled() {

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .enabled(true)
                .build();

        Assertions.assertTrue(event.getEnabled());
    }

    @Test
    public void whenEventToString() {

        AwxHostCreatedEvent event = event();

        String expected = "AwxHostCreatedEvent(id=96859caf-c3ae-4841-a17f-abb50732864c, awxInventoryId=e8f827a2-1fc8-4536-a6f9-8ff2785ccca1, instanceId=instanceId, awxId=1, hostname=hostname, description=description, enabled=true)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        AwxHostCreatedEvent event = event();

        Assertions.assertEquals(-1173163872, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        AwxHostCreatedEvent event1 = event();
        AwxHostCreatedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        AwxHostCreatedEvent event = event();

        Assertions.assertNotEquals(event, AwxHostCreatedEvent.builder().build());
    }

    private AwxHostCreatedEvent event() {

        return AwxHostCreatedEvent.builder()
                .id(UUID.fromString("96859caf-c3ae-4841-a17f-abb50732864c"))
                .awxInventoryId(UUID.fromString("e8f827a2-1fc8-4536-a6f9-8ff2785ccca1"))
                .instanceId("instanceId")
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();
    }
}
