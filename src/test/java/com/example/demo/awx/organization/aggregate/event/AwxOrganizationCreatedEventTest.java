package com.example.demo.awx.organization.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxOrganizationCreatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasAwxIdThenReturnAwxId() {

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, event.getAwxId());
    }

    @Test
    public void whenEventHasNameThenReturnName() {

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", event.getName());
    }

    @Test
    public void whenEventHasDescriptionThenReturnDescription() {

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", event.getDescription());
    }

    @Test
    public void whenEventToString() {

        AwxOrganizationCreatedEvent event = event();

        String expected = "AwxOrganizationCreatedEvent(id=2bd0520a-4834-4e8d-8382-077195298dc0, awxId=1, name=name, description=description)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        AwxOrganizationCreatedEvent event = event();

        Assertions.assertEquals(-855618049, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        AwxOrganizationCreatedEvent event1 = event();
        AwxOrganizationCreatedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        AwxOrganizationCreatedEvent event = event();

        Assertions.assertNotEquals(event, AwxOrganizationCreatedEvent.builder().build());
    }

    private AwxOrganizationCreatedEvent event() {

        return AwxOrganizationCreatedEvent.builder()
                .id(UUID.fromString("2bd0520a-4834-4e8d-8382-077195298dc0"))
                .awxId(1L)
                .name("name")
                .description("description")
                .build();
    }
}
