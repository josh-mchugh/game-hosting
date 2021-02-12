package com.example.demo.awx.notification.entity.service;

import com.example.demo.awx.notification.aggregate.event.AwxNotificationCreatedEvent;
import com.example.demo.awx.notification.entity.model.AwxNotification;
import com.example.demo.awx.organization.entity.model.AwxOrganization;
import com.example.demo.sample.SampleBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxNotificationServiceCreatedTest {

    @Autowired
    private IAwxNotificationService awxNotificationService;

    @Autowired
    private SampleBuilder sampleBuilder;

    private AwxOrganization awxOrganization;

    @BeforeEach
    public void setup() {

         awxOrganization = sampleBuilder.builder()
                .awxOrganization()
                .build()
                .getAwxOrganization();
    }

    @Test
    public void whenCreateRequestIsValidThenReturnNotNull() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(event);

        Assertions.assertNotNull(awxNotification);
    }

    @Test
    public void whenCreateRequestHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> awxNotificationService.handleCreated(null));
    }

    @Test
    public void whenCreateRequestIsValidThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(id)
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(event);

        Assertions.assertEquals(id, awxNotification.getId());
    }

    @Test
    public void whenCreateRequestHasNullAwxOrganizationIdThenThrowException() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(null)
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxNotificationService.handleCreated(event));
    }

    @Test
    public void whenCreateRequestHasInvalidAwxOrganizationIdThenThrowException() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(UUID.randomUUID().toString())
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxNotificationService.handleCreated(event));
    }

    @Test
    public void whenCreateRequestHasAwxIdThenReturnAwxID() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(event);

        Assertions.assertEquals(1L, awxNotification.getAwxId());
    }

    @Test
    public void whenCreateRequestHasNullAwxIdThenThrowException() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(awxOrganization.getId())
                .awxId(null)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxNotificationService.handleCreated(event));
    }

    @Test
    public void whenCreateRequestHasNameThenReturnName() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(event);

        Assertions.assertEquals("name", awxNotification.getName());
    }

    @Test
    public void whenCreateHasNullNameThenThrowException() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name(null)
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxNotificationService.handleCreated(event));
    }

    @Test
    public void whenCreateRequestHasDescriptionThenReturnDescription() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(event);

        Assertions.assertEquals("description", awxNotification.getDescription());
    }

    @Test
    public void whenCreateRequestNullDescriptionThenReturnNull() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description(null)
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(event);

        Assertions.assertNull(awxNotification.getDescription());
    }

    @Test
    public void whenCreateRequestHasTypeThenReturnType() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .type("slack")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(event);

        Assertions.assertEquals("slack", awxNotification.getType());
    }

    @Test
    public void whenCreateRequestHasNullTypeThenReturnNull() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .type(null)
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(event);

        Assertions.assertNull(awxNotification.getType());
    }

    @Test
    public void whenCreateRequestHasWebhookCallbackUrlThenReturnWebhookCallbackUrl() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(event);

        Assertions.assertEquals("callback url", awxNotification.getWebhookCallbackUrl());
    }

    @Test
    public void whenCreateRequestHasNullWebhookCallbackUrlThenReturnNull() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl(null)
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(event);

        Assertions.assertNull(awxNotification.getWebhookCallbackUrl());
    }
}
