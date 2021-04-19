package com.example.demo.awx.notification.feign.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NotificationApiTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        NotificationApi model = new NotificationApi();
        model.setId(1L);

        Assertions.assertEquals(1L, model.getId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        NotificationApi model = new NotificationApi();
        model.setName("name");

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        NotificationApi model = new NotificationApi();
        model.setDescription("description");

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelHasNotificationConfigurationThenReturnNotificationConfiguration() {

        NotificationConfiguration notificationConfiguration = NotificationConfiguration.builder().build();

        NotificationApi model = new NotificationApi();
        model.setNotificationConfiguration(notificationConfiguration);

        Assertions.assertEquals(notificationConfiguration, model.getNotificationConfiguration());
    }

    @Test
    public void whenModelHasTypeThenReturnType() {

        NotificationApi model = new NotificationApi();
        model.setType("type");

        Assertions.assertEquals("type", model.getType());
    }

    @Test
    public void whenModelHasOrganizationIdThenReturnOrganizationId() {

        NotificationApi model = new NotificationApi();
        model.setOrganizationId(1L);

        Assertions.assertEquals(1L, model.getOrganizationId());
    }

    @Test
    public void whenModelToString() {

        NotificationApi model = model();

        String expected = "NotificationApi(id=1, name=name, description=description, notificationConfiguration=NotificationConfiguration(url=null, headers={}), type=type, organizationId=1)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        NotificationApi model = model();

        Assertions.assertEquals(-1714826190, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        NotificationApi model1 = model();
        NotificationApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        NotificationApi model = model();

        Assertions.assertNotEquals(model, new NotificationApi());
    }

    private NotificationApi model() {

        NotificationApi model = new NotificationApi();
        model.setId(1L);
        model.setName("name");
        model.setDescription("description");
        model.setNotificationConfiguration(NotificationConfiguration.builder().build());
        model.setType("type");
        model.setOrganizationId(1L);

        return model;
    }
}
