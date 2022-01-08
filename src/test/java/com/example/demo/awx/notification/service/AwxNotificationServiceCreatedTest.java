package com.example.demo.awx.notification.service;

import com.example.demo.awx.notification.service.model.AwxNotificationCreateRequest;
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
    private AwxNotificationService awxNotificationService;

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

        AwxNotificationCreateRequest request = AwxNotificationCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(request);

        Assertions.assertNotNull(awxNotification);
    }

    @Test
    public void whenCreateRequestHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> awxNotificationService.handleCreated(null));
    }

    @Test
    public void whenCreateRequestIsValidThenReturnIdIsNotNull() {

        AwxNotificationCreateRequest request = AwxNotificationCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(request);

        Assertions.assertNotNull(awxNotification.getId());
    }

    @Test
    public void whenCreateRequestHasNullAwxOrganizationIdThenThrowException() {

        AwxNotificationCreateRequest request = AwxNotificationCreateRequest.builder()
                .awxOrganizationId(null)
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> awxNotificationService.handleCreated(request));
    }

    @Test
    public void whenCreateRequestHasInvalidAwxOrganizationIdThenThrowException() {

        AwxNotificationCreateRequest request = AwxNotificationCreateRequest.builder()
                .awxOrganizationId(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxNotificationService.handleCreated(request));
    }

    @Test
    public void whenCreateRequestHasAwxIdThenReturnAwxID() {

        AwxNotificationCreateRequest request = AwxNotificationCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(request);

        Assertions.assertEquals(1L, awxNotification.getAwxId());
    }

    @Test
    public void whenCreateRequestHasNullAwxIdThenThrowException() {

        AwxNotificationCreateRequest request = AwxNotificationCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(null)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxNotificationService.handleCreated(request));
    }

    @Test
    public void whenCreateRequestHasNameThenReturnName() {

        AwxNotificationCreateRequest request = AwxNotificationCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(request);

        Assertions.assertEquals("name", awxNotification.getName());
    }

    @Test
    public void whenCreateHasNullNameThenThrowException() {

        AwxNotificationCreateRequest request = AwxNotificationCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name(null)
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxNotificationService.handleCreated(request));
    }

    @Test
    public void whenCreateRequestHasDescriptionThenReturnDescription() {

        AwxNotificationCreateRequest request = AwxNotificationCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(request);

        Assertions.assertEquals("description", awxNotification.getDescription());
    }

    @Test
    public void whenCreateRequestNullDescriptionThenReturnNull() {

        AwxNotificationCreateRequest request = AwxNotificationCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description(null)
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(request);

        Assertions.assertNull(awxNotification.getDescription());
    }

    @Test
    public void whenCreateRequestHasTypeThenReturnType() {

        AwxNotificationCreateRequest request = AwxNotificationCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .type("slack")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(request);

        Assertions.assertEquals("slack", awxNotification.getType());
    }

    @Test
    public void whenCreateRequestHasNullTypeThenReturnNull() {

        AwxNotificationCreateRequest request = AwxNotificationCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .type(null)
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(request);

        Assertions.assertNull(awxNotification.getType());
    }

    @Test
    public void whenCreateRequestHasWebhookCallbackUrlThenReturnWebhookCallbackUrl() {

        AwxNotificationCreateRequest request = AwxNotificationCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(request);

        Assertions.assertEquals("callback url", awxNotification.getWebhookCallbackUrl());
    }

    @Test
    public void whenCreateRequestHasNullWebhookCallbackUrlThenReturnNull() {

        AwxNotificationCreateRequest request = AwxNotificationCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl(null)
                .build();

        AwxNotification awxNotification = awxNotificationService.handleCreated(request);

        Assertions.assertNull(awxNotification.getWebhookCallbackUrl());
    }
}
