package com.example.demo.awx.notification.entity;

import com.example.demo.awx.organization.entity.AwxOrganizationEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class AwxNotificationEntityTest {

    @Test
    public void whenEntityHasIdThenReturnId() {

        AwxNotificationEntity entity = new AwxNotificationEntity();
        entity.setId("id");

        Assertions.assertEquals("id", entity.getId());
    }

    @Test
    public void whenEntityHasUUIDThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxNotificationEntity entity = new AwxNotificationEntity();
        entity.setId(id);

        Assertions.assertEquals(id.toString(), entity.getId());
    }

    @Test
    public void whenEntityHasCreatedByThenReturnCreatedBy() {

        AwxNotificationEntity entity = new AwxNotificationEntity();
        entity.setCreatedBy("created-by");

        Assertions.assertEquals("created-by", entity.getCreatedBy());
    }

    @Test
    public void whenEntityHasCreatedDateThenCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        AwxNotificationEntity entity = new AwxNotificationEntity();
        entity.setCreatedDate(createdDate);

        Assertions.assertEquals(createdDate, entity.getCreatedDate());
    }

    @Test
    public void whenEntityHasLastModifiedByThenLastModifiedBy() {

        AwxNotificationEntity entity = new AwxNotificationEntity();
        entity.setLastModifiedBy("last-modified-by");

        Assertions.assertEquals("last-modified-by", entity.getLastModifiedBy());
    }

    @Test
    public void whenEntityHasLastModifiedDateThenLastModifiedDate() {

        LocalDateTime lastModifiedDate = LocalDateTime.now();

        AwxNotificationEntity entity = new AwxNotificationEntity();
        entity.setLastModifiedDate(lastModifiedDate);

        Assertions.assertEquals(lastModifiedDate, entity.getLastModifiedDate());
    }

    @Test
    public void whenEntityHasVersionThenReturnVersion() {

        AwxNotificationEntity entity = new AwxNotificationEntity();
        entity.setVersion(1L);

        Assertions.assertEquals(1L, entity.getVersion());
    }

    @Test
    public void whenEntityHasAwxOrganizationThenReturnOrganization() {

        AwxOrganizationEntity awxOrganizationEntity = new AwxOrganizationEntity();

        AwxNotificationEntity entity = new AwxNotificationEntity();
        entity.setAwxOrganizationEntity(awxOrganizationEntity);

        Assertions.assertEquals(awxOrganizationEntity, entity.getAwxOrganizationEntity());
    }

    @Test
    public void whenEntityHasAwxIdThenReturnAwxId() {

        AwxNotificationEntity entity = new AwxNotificationEntity();
        entity.setAwxId(1L);

        Assertions.assertEquals(1L, entity.getAwxId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        AwxNotificationEntity entity = new AwxNotificationEntity();
        entity.setName("name");

        Assertions.assertEquals("name", entity.getName());
    }

    @Test
    public void whenEntityHasDescriptionThenReturnDescription() {

        AwxNotificationEntity entity = new AwxNotificationEntity();
        entity.setDescription("description");

        Assertions.assertEquals("description", entity.getDescription());
    }

    @Test
    public void whenEntityHasTypeTypeThenReturnType() {

        AwxNotificationEntity entity = new AwxNotificationEntity();
        entity.setType("type");

        Assertions.assertEquals("type", entity.getType());
    }

    @Test
    public void whenEntityHasWebhookCallBackUrlThenReturnWebHookCallBackUrl() {

        AwxNotificationEntity entity = new AwxNotificationEntity();
        entity.setWebhookCallBackUrl("url");

        Assertions.assertEquals("url", entity.getWebhookCallBackUrl());
    }
}
