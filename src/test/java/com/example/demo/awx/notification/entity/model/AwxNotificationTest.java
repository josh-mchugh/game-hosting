package com.example.demo.awx.notification.entity.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxNotificationTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxNotification model = AwxNotification.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasAwxIdThenAwxId() {

        AwxNotification model = AwxNotification.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, model.getAwxId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        AwxNotification model = AwxNotification.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        AwxNotification model = AwxNotification.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelHasTypeThenReturnType() {

        AwxNotification model = AwxNotification.builder()
                .type("type")
                .build();

        Assertions.assertEquals("type", model.getType());
    }

    @Test
    public void whenModelHasWebhookCallBackUrlThenReturnWebhookCallBackUrl() {

        AwxNotification model = AwxNotification.builder()
                .webhookCallbackUrl("url")
                .build();

        Assertions.assertEquals("url", model.getWebhookCallbackUrl());
    }

    @Test
    public void whenModelToString() {

        AwxNotification model = model();

        String expected = "AwxNotification(id=d77d385a-e625-42a4-8422-3bca6e8e2193, awxId=1, name=name, description=description, type=type, webhookCallbackUrl=url)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AwxNotification model = model();

        Assertions.assertEquals(62697201, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AwxNotification model1 = model();
        AwxNotification model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        AwxNotification model = model();

        Assertions.assertNotEquals(model, AwxNotification.builder().build());
    }

    private AwxNotification model() {

        return AwxNotification.builder()
                .id(UUID.fromString("d77d385a-e625-42a4-8422-3bca6e8e2193"))
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallbackUrl("url")
                .build();
    }
}
