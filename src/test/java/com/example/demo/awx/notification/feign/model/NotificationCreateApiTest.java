package com.example.demo.awx.notification.feign.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NotificationCreateApiTest {

    @Test
    public void whenModelHasNameThenReturnName() {

        NotificationCreateApi model = NotificationCreateApi.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        NotificationCreateApi model = NotificationCreateApi.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelHasOrganizationIdThenReturnOrganizationId() {

        NotificationCreateApi model = NotificationCreateApi.builder()
                .organizationId(1L)
                .build();

        Assertions.assertEquals(1L, model.getOrganizationId());
    }

    @Test
    public void whenModelHasTypeThenReturnType() {

        NotificationCreateApi model = NotificationCreateApi.builder()
                .type("type")
                .build();

        Assertions.assertEquals("type", model.getType());
    }

    @Test
    public void whenModelHasNofiticationConfigurationThenReturnNotificationConfiguration() {

        NotificationConfiguration configuration = NotificationConfiguration.builder().build();

        NotificationCreateApi model = NotificationCreateApi.builder()
                .notificationConfiguration(configuration)
                .build();

        Assertions.assertEquals(configuration, model.getNotificationConfiguration());
    }

    @Test
    public void whenModelToString() {

        NotificationCreateApi model = model();

        String expected = "NotificationCreateApi(name=name, description=description, organizationId=1, type=type, notificationConfiguration=NotificationConfiguration(url=null, headers={}))";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        NotificationCreateApi model = model();

        Assertions.assertEquals(-738650535, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        NotificationCreateApi model1 = model();
        NotificationCreateApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        NotificationCreateApi model = model();

        Assertions.assertNotEquals(model, NotificationCreateApi.builder().build());
    }

    private NotificationCreateApi model() {

        NotificationConfiguration configuration = NotificationConfiguration.builder().build();

        return NotificationCreateApi.builder()
                .name("name")
                .description("description")
                .organizationId(1L)
                .type("type")
                .notificationConfiguration(configuration)
                .build();
    }
}
