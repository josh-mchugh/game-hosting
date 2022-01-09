package com.example.demo.awx.notification.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AwxNotificationCreateRequestTest {

    @Test
    public void whenModelHasAwxOrganizationIdThenReturnAwxOrganizationId() {

        AwxNotificationCreateRequest model = AwxNotificationCreateRequest.builder()
                .awxOrganizationId("awxOrganizationId")
                .build();

        Assertions.assertEquals("awxOrganizationId", model.getAwxOrganizationId());
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

        String expected = "AwxNotificationCreateRequest(awxOrganizationId=awxOrganizationId, awxId=1, name=name, description=description, type=type, webhookCallBackUrl=url)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AwxNotificationCreateRequest model = model();

        Assertions.assertEquals(2100572334, model.hashCode());
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
                .awxOrganizationId("awxOrganizationId")
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("url")
                .build();
    }
}
