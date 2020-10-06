package com.example.demo.awx.notification.entity;

import com.example.demo.awx.notification.aggregate.event.AwxNotificationCreatedEvent;
import com.example.demo.awx.notification.entity.model.AwxNotification;
import com.example.demo.awx.notification.entity.service.IAwxNotificationService;
import com.example.demo.awx.organization.model.AwxOrganization;
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
public class AwxNotificationServiceHandleCreatedTest {

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
                .organizationId(awxOrganization.getOrganizationId())
                .notificationId(1L)
                .name("name")
                .description("description")
                .notificationType("notification type")
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
                .organizationId(awxOrganization.getOrganizationId())
                .notificationId(1L)
                .name("name")
                .description("description")
                .notificationType("notification type")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(event);

        Assertions.assertEquals(id.toString(), awxNotification.getId());
    }

    @Test
    public void whenCreateRequestHasNullOrganizationIdThenThrowException() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(null)
                .notificationId(1L)
                .name("name")
                .description("description")
                .notificationType("notification type")
                .webhookCallBackUrl("callback url")
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxNotificationService.handleCreated(event));
    }

    @Test
    public void whenCreateRequestHasInvalidOrganizationIdThenThrowException() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(999L)
                .notificationId(1L)
                .name("name")
                .description("description")
                .notificationType("notification type")
                .webhookCallBackUrl("callback url")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxNotificationService.handleCreated(event));
    }

    @Test
    public void whenCreateRequestHasNotificationIdThenReturnNotificationId() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .notificationId(1L)
                .name("name")
                .description("description")
                .notificationType("notification type")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(event);

        Assertions.assertEquals(1L, awxNotification.getNotificationId());
    }

    @Test
    public void whenCreateRequestHasNullNotificationIdThenThrowException() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .notificationId(null)
                .name("name")
                .description("description")
                .notificationType("notification type")
                .webhookCallBackUrl("callback url")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxNotificationService.handleCreated(event));
    }

    @Test
    public void whenCreateRequestHasNameThenReturnName() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .notificationId(1L)
                .name("name")
                .description("description")
                .notificationType("notification type")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(event);

        Assertions.assertEquals("name", awxNotification.getName());
    }

    @Test
    public void whenCreateHasNullNameThenThrowException() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .notificationId(1L)
                .name(null)
                .description("description")
                .notificationType("notification type")
                .webhookCallBackUrl("callback url")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxNotificationService.handleCreated(event));
    }

    @Test
    public void whenCreateRequestHasDescriptionThenReturnDescription() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .notificationId(1L)
                .name("name")
                .description("description")
                .notificationType("notification type")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(event);

        Assertions.assertEquals("description", awxNotification.getDescription());
    }

    @Test
    public void whenCreateRequestNullDescriptionThenReturnNull() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .notificationId(1L)
                .name("name")
                .description(null)
                .notificationType("notification type")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(event);

        Assertions.assertNull(awxNotification.getDescription());
    }

    @Test
    public void whenCreateRequestHasNotificationTypeThenReturnNotificationType() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .notificationId(1L)
                .name("name")
                .description("description")
                .notificationType("slack")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(event);

        Assertions.assertEquals("slack", awxNotification.getNotificationType());
    }

    @Test
    public void whenCreateRequestHasNullNotificationTypeThenReturnNull() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .notificationId(1L)
                .name("name")
                .description("description")
                .notificationType(null)
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(event);

        Assertions.assertNull(awxNotification.getNotificationType());
    }

    @Test
    public void whenCreateRequestHasWebhookCallbackUrlThenReturnWebhookCallbackUrl() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .notificationId(1L)
                .name("name")
                .description("description")
                .notificationType("notification type")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(event);

        Assertions.assertEquals("callback url", awxNotification.getWebhookCallbackUrl());
    }

    @Test
    public void whenCreateRequestHasNullWebhookCallbackUrlThenReturnNull() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .notificationId(1L)
                .name("name")
                .description("description")
                .notificationType("notification type")
                .webhookCallBackUrl(null)
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(event);

        Assertions.assertNull(awxNotification.getWebhookCallbackUrl());
    }
}
