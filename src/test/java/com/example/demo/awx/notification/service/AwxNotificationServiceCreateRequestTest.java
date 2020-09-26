package com.example.demo.awx.notification.service;

import com.example.demo.awx.notification.model.AwxNotification;
import com.example.demo.awx.notification.service.model.AwxNotificationCreateRequest;
import com.example.demo.awx.organization.model.AwxOrganization;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.util.TestAwxNotificationCreateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxNotificationServiceCreateRequestTest {

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

        AwxNotificationCreateRequest request = TestAwxNotificationCreateRequest.builder()
                .awxOrganization(awxOrganization)
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreateNotification(request);

        Assertions.assertNotNull(awxNotification);
    }

    @Test
    public void whenCreateRequestHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> awxNotificationService.handleCreateNotification(null));
    }

    @Test
    public void whenCreateRequestIsValidThenReturnId() {

        AwxNotificationCreateRequest request = TestAwxNotificationCreateRequest.builder()
                .awxOrganization(awxOrganization)
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreateNotification(request);

        Assertions.assertNotNull(awxNotification.getId());
    }

    @Test
    public void whenCreateRequestHasNullOrganizationIdThenThrowException() {

        AwxNotificationCreateRequest request = TestAwxNotificationCreateRequest.builder()
                .awxOrganizationId(null)
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxNotificationService.handleCreateNotification(request));
    }

    @Test
    public void whenCreateRequestHasInvalidOrganizationIdThenThrowException() {

        AwxNotificationCreateRequest request = TestAwxNotificationCreateRequest.builder()
                .awxOrganizationId(2L)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxNotificationService.handleCreateNotification(request));
    }

    @Test
    public void whenCreateRequestHasNotificationIdThenReturnNotificationId() {

        AwxNotificationCreateRequest request = TestAwxNotificationCreateRequest.builder()
                .awxOrganization(awxOrganization)
                .notificationId(3L)
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreateNotification(request);

        Assertions.assertEquals(3L, awxNotification.getNotificationId());
    }

    @Test
    public void whenCreateRequestHasNullNotificationIdThenThrowException() {

        AwxNotificationCreateRequest request = TestAwxNotificationCreateRequest.builder()
                .awxOrganization(awxOrganization)
                .notificationId(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxNotificationService.handleCreateNotification(request));
    }

    @Test
    public void whenCreateRequestHasNameThenReturnName() {

        AwxNotificationCreateRequest request = TestAwxNotificationCreateRequest.builder()
                .awxOrganization(awxOrganization)
                .name("name")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreateNotification(request);

        Assertions.assertEquals("name", awxNotification.getName());
    }

    @Test
    public void whenCreateHasNullNameThenThrowException() {

        AwxNotificationCreateRequest request = TestAwxNotificationCreateRequest.builder()
                .awxOrganization(awxOrganization)
                .name(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxNotificationService.handleCreateNotification(request));
    }

    @Test
    public void whenCreateRequestHasDescriptionThenReturnDescription() {

        AwxNotificationCreateRequest request = TestAwxNotificationCreateRequest.builder()
                .awxOrganization(awxOrganization)
                .description("description")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreateNotification(request);

        Assertions.assertEquals("description", awxNotification.getDescription());
    }

    @Test
    public void whenCreateRequestNullDescriptionThenReturnNull() {

        AwxNotificationCreateRequest request = TestAwxNotificationCreateRequest.builder()
                .awxOrganization(awxOrganization)
                .description(null)
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreateNotification(request);

        Assertions.assertNull(awxNotification.getDescription());
    }

    @Test
    public void whenCreateRequestHasNotificationTypeThenReturnNotificationType() {

        AwxNotificationCreateRequest request = TestAwxNotificationCreateRequest.builder()
                .awxOrganization(awxOrganization)
                .notificationType("slack")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreateNotification(request);

        Assertions.assertEquals("slack", awxNotification.getNotificationType());
    }

    @Test
    public void whenCreateRequestHasNullNotificationTypeThenReturnNull() {

        AwxNotificationCreateRequest request = TestAwxNotificationCreateRequest.builder()
                .awxOrganization(awxOrganization)
                .notificationType(null)
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreateNotification(request);

        Assertions.assertNull(awxNotification.getNotificationType());
    }

    @Test
    public void whenCreateRequestHasWebhookCallbackUrlThenReturnWebhookCallbackUrl() {

        AwxNotificationCreateRequest request = TestAwxNotificationCreateRequest.builder()
                .awxOrganization(awxOrganization)
                .webhookCallbackUrl("webhook callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreateNotification(request);

        Assertions.assertEquals("webhook callback url", awxNotification.getWebhookCallbackUrl());
    }

    @Test
    public void whenCreateRequestHasNullWebhookCallbackUrlThenReturnNull() {

        AwxNotificationCreateRequest request = TestAwxNotificationCreateRequest.builder()
                .awxOrganization(awxOrganization)
                .webhookCallbackUrl(null)
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreateNotification(request);

        Assertions.assertNull(awxNotification.getWebhookCallbackUrl());
    }
}
