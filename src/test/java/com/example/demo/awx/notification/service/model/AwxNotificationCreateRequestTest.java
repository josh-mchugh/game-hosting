package com.example.demo.awx.notification.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxNotificationCreateRequestTest {

    @Test
    public void whenModelHasAwxOrganizationIdThenReturnAwxOrganizationId() {

        UUID awxOrganizationUId = UUID.randomUUID();

        AwxNotificationCreateRequest model = AwxNotificationCreateRequest.builder()
                .awxOrganizationId(awxOrganizationUId)
                .build();

        Assertions.assertEquals(awxOrganizationUId, model.getAwxOrganizationId());
    }

    @Test
    public void whenModelHasAwxIdThenReturnAwxId() {

        AwxNotificationCreateRequest model = AwxNotificationCreateRequest.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, model.getAwxId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        AwxNotificationCreateRequest model = AwxNotificationCreateRequest.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        AwxNotificationCreateRequest model = AwxNotificationCreateRequest.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelHasTypeThenReturnType() {

        AwxNotificationCreateRequest model = AwxNotificationCreateRequest.builder()
                .type("type")
                .build();

        Assertions.assertEquals("type", model.getType());
    }

    @Test
    public void whenModelHasWebHookCallBackUrlThenReturnWebHookCallBackUrl() {

        AwxNotificationCreateRequest model = AwxNotificationCreateRequest.builder()
                .webhookCallBackUrl("url")
                .build();

        Assertions.assertEquals("url", model.getWebhookCallBackUrl());
    }

    @Test
    public void whenModelToString() {

        AwxNotificationCreateRequest model = model();

        String expected = "AwxNotificationCreateRequest(awxOrganizationId=e8575c27-5368-4782-9b41-e94081471d22, awxId=1, name=name, description=description, type=type, webhookCallBackUrl=url)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AwxNotificationCreateRequest model = model();

        Assertions.assertEquals(-92170699, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AwxNotificationCreateRequest model1 = model();
        AwxNotificationCreateRequest model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        AwxNotificationCreateRequest model = model();

        Assertions.assertNotEquals(model, AwxNotificationCreateRequest.builder().build());
    }

    private AwxNotificationCreateRequest model() {

        return AwxNotificationCreateRequest.builder()
                .awxOrganizationId(UUID.fromString("e8575c27-5368-4782-9b41-e94081471d22"))
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("url")
                .build();
    }
}
