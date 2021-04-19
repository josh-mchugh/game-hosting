package com.example.demo.awx.inventory.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxInventoryCreatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxInventoryCreatedEvent event = AwxInventoryCreatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasAwxOrganizationIdThenReturnAwxOrganizationId() {

        UUID awxOrganizationId = UUID.randomUUID();

        AwxInventoryCreatedEvent event = AwxInventoryCreatedEvent.builder()
                .awxOrganizationId(awxOrganizationId)
                .build();

        Assertions.assertEquals(awxOrganizationId, event.getAwxOrganizationId());
    }

    @Test
    public void whenEventHasAwxIdThenReturnAwxId() {

        AwxInventoryCreatedEvent event = AwxInventoryCreatedEvent.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, event.getAwxId());
    }

    @Test
    public void whenEventHasNameThenReturnName() {

        AwxInventoryCreatedEvent event = AwxInventoryCreatedEvent.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", event.getName());
    }

    @Test
    public void whenEventHasDescriptionThenReturnDescription() {

        AwxInventoryCreatedEvent event = AwxInventoryCreatedEvent.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", event.getDescription());
    }

    @Test
    public void whenEventToString() {

        AwxInventoryCreatedEvent event = event();

        String expected = "AwxInventoryCreatedEvent(id=cedd3764-05ab-4544-b62e-dc8269398ca4, awxOrganizationId=a1c2251e-411e-44f8-a035-7d317a81ca97, awxId=1, name=name, description=description)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        AwxInventoryCreatedEvent event = event();

        Assertions.assertEquals(-1376483245, event.hashCode());
    }

    @Test
    public void whenEventToEquals() {

        AwxInventoryCreatedEvent event1 = event();
        AwxInventoryCreatedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        AwxInventoryCreatedEvent event = event();

        Assertions.assertNotEquals(event, AwxInventoryCreatedEvent.builder().build());
    }

    private AwxInventoryCreatedEvent event() {

        return AwxInventoryCreatedEvent.builder()
                .id(UUID.fromString("cedd3764-05ab-4544-b62e-dc8269398ca4"))
                .awxOrganizationId(UUID.fromString("a1c2251e-411e-44f8-a035-7d317a81ca97"))
                .awxId(1L)
                .name("name")
                .description("description")
                .build();
    }
}
