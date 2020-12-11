package com.example.demo.awx.notification.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxNotificationCreatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasAwxOrganizationIdThenReturnAwxOrganizationId() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .awxOrganizationId("awxOrganizationId")
                .build();

        Assertions.assertEquals("awxOrganizationId", event.getAwxOrganizationId());
    }

    @Test
    public void whenEventHasAwxIdThenReturnAwxId() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, event.getAwxId());
    }

    @Test
    public void whenEventHasNameThenReturnName() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", event.getName());
    }

    @Test
    public void whenEventHasDescriptionThenReturnDescription() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", event.getDescription());
    }

    @Test
    public void whenEventHasTypeThenReturnType() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .type("type")
                .build();

        Assertions.assertEquals("type", event.getType());
    }

    @Test
    public void whenEventHasWebHookCallBackUrlThenReturnWebHookCallBackUrl() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .webhookCallBackUrl("url")
                .build();

        Assertions.assertEquals("url", event.getWebhookCallBackUrl());
    }

    @Test
    public void whenEventToString() {

        AwxNotificationCreatedEvent event = event();

        String expected = "AwxNotificationCreatedEvent(id=f67bc48b-8dbb-46a2-89d5-a67c85ef7073, awxOrganizationId=awxOrganizationId, awxId=1, name=name, description=description, type=type, webhookCallBackUrl=url)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        AwxNotificationCreatedEvent event = event();

        Assertions.assertEquals(563910292, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        AwxNotificationCreatedEvent event1 = event();
        AwxNotificationCreatedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        AwxNotificationCreatedEvent event = event();

        Assertions.assertNotEquals(event, AwxNotificationCreatedEvent.builder().build());
    }

    private AwxNotificationCreatedEvent event() {

        return AwxNotificationCreatedEvent.builder()
                .id(UUID.fromString("f67bc48b-8dbb-46a2-89d5-a67c85ef7073"))
                .awxOrganizationId("awxOrganizationId")
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("url")
                .build();
    }
}
