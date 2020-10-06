package com.example.demo.awx.notification.entity;

import com.example.demo.awx.notification.entity.mapper.AwxNotificationMapper;
import com.example.demo.awx.notification.entity.model.AwxNotification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AwxNotificationMapperTest {

    @Test
    public void whenEntityIsNullThenReturnNull() {

        Assertions.assertNull(AwxNotificationMapper.map(null));
    }

    @Test
    public void whenEntityIsValidThenReturnNotNull() {

        Assertions.assertNotNull(AwxNotificationMapper.map(new AwxNotificationEntity()));
    }

    @Test
    public void whenEntityHasIdThenReturnId() {

        AwxNotificationEntity entity = new AwxNotificationEntity();
        entity.setId("id");

        AwxNotification awxNotification = AwxNotificationMapper.map(entity);

        Assertions.assertEquals("id", awxNotification.getId());
    }

    @Test
    public void whenEntityHasNullIdThenReturnNull() {

        AwxNotification awxNotification = AwxNotificationMapper.map(new AwxNotificationEntity());

        Assertions.assertNull(awxNotification.getId());
    }

    @Test
    public void whenEntityHasNotificationIdThenReturnNotificationId() {

        AwxNotificationEntity entity = new AwxNotificationEntity();
        entity.setNotificationId(1L);

        AwxNotification awxNotification = AwxNotificationMapper.map(entity);

        Assertions.assertEquals(1L, awxNotification.getNotificationId());
    }

    @Test
    public void whenEntityHasNullNotificationIdThenReturnNull() {

        AwxNotification awxNotification = AwxNotificationMapper.map(new AwxNotificationEntity());

        Assertions.assertNull(awxNotification.getNotificationId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        AwxNotificationEntity entity = new AwxNotificationEntity();
        entity.setName("name");

        AwxNotification awxNotification = AwxNotificationMapper.map(entity);

        Assertions.assertEquals("name", awxNotification.getName());
    }

    @Test
    public void whenEntityHasNullNameThenReturnNull() {

        AwxNotification awxNotification = AwxNotificationMapper.map(new AwxNotificationEntity());

        Assertions.assertNull(awxNotification.getName());
    }

    @Test
    public void whenEntityHasDescriptionThenReturnDescription() {

        AwxNotificationEntity entity = new AwxNotificationEntity();
        entity.setDescription("description");

        AwxNotification awxNotification = AwxNotificationMapper.map(entity);

        Assertions.assertEquals("description", awxNotification.getDescription());
    }

    @Test
    public void whenEntityHasNullDescriptionThenReturnNull() {

        AwxNotification awxNotification = AwxNotificationMapper.map(new AwxNotificationEntity());

        Assertions.assertNull(awxNotification.getDescription());
    }

    @Test
    public void whenEntityHasNotificationTypeThenReturnNotificationType() {

        AwxNotificationEntity entity = new AwxNotificationEntity();
        entity.setNotificationType("notification type");

        AwxNotification awxNotification = AwxNotificationMapper.map(entity);

        Assertions.assertEquals("notification type", awxNotification.getNotificationType());
    }

    @Test
    public void whenEntityHasNullNotificationTypeThenReturnNull() {

        AwxNotification awxNotification = AwxNotificationMapper.map(new AwxNotificationEntity());

        Assertions.assertNull(awxNotification.getNotificationType());
    }

    @Test
    public void whenEntityHasWebhookCallbackUrlThenReturnWebhookCallbackUrl() {

        AwxNotificationEntity entity = new AwxNotificationEntity();
        entity.setWebhookCallBackUrl("url");

        AwxNotification awxNotification = AwxNotificationMapper.map(entity);

        Assertions.assertEquals("url", awxNotification.getWebhookCallbackUrl());
    }

    @Test
    public void whenEntityHasNullWebhookCallbackUrlThenReturnNull() {

        AwxNotification awxNotification = AwxNotificationMapper.map(new AwxNotificationEntity());

        Assertions.assertNull(awxNotification.getWebhookCallbackUrl());
    }
}
