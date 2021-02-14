package com.example.demo.awx.organization.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class AwxOrganizationEntityTest {

    @Test
    public void whenEntityHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxOrganizationEntity entity = new AwxOrganizationEntity();
        entity.setId(id);

        Assertions.assertEquals(id, entity.getId());
    }

    @Test
    public void whenEntityHasCreatedByThenReturnCreatedBy() {

        AwxOrganizationEntity entity = new AwxOrganizationEntity();
        entity.setCreatedBy("created-by");

        Assertions.assertEquals("created-by", entity.getCreatedBy());
    }

    @Test
    public void whenEntityHasCreatedDateThenCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        AwxOrganizationEntity entity = new AwxOrganizationEntity();
        entity.setCreatedDate(createdDate);

        Assertions.assertEquals(createdDate, entity.getCreatedDate());
    }

    @Test
    public void whenEntityHasLastModifiedByThenLastModifiedBy() {

        AwxOrganizationEntity entity = new AwxOrganizationEntity();
        entity.setLastModifiedBy("last-modified-by");

        Assertions.assertEquals("last-modified-by", entity.getLastModifiedBy());
    }

    @Test
    public void whenEntityHasLastModifiedDateThenLastModifiedDate() {

        LocalDateTime lastModifiedDate = LocalDateTime.now();

        AwxOrganizationEntity entity = new AwxOrganizationEntity();
        entity.setLastModifiedDate(lastModifiedDate);

        Assertions.assertEquals(lastModifiedDate, entity.getLastModifiedDate());
    }

    @Test
    public void whenEntityHasVersionThenReturnVersion() {

        AwxOrganizationEntity entity = new AwxOrganizationEntity();
        entity.setVersion(1L);

        Assertions.assertEquals(1L, entity.getVersion());
    }

    @Test
    public void whenEntityHasAwxIdThenReturnAwxId() {

        AwxOrganizationEntity entity = new AwxOrganizationEntity();
        entity.setAwxId(1L);

        Assertions.assertEquals(1L, entity.getAwxId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        AwxOrganizationEntity entity = new AwxOrganizationEntity();
        entity.setName("name");

        Assertions.assertEquals("name", entity.getName());
    }

    @Test
    public void whenEntityHasDescriptionThenReturnDescription() {

        AwxOrganizationEntity entity = new AwxOrganizationEntity();
        entity.setDescription("description");

        Assertions.assertEquals("description", entity.getDescription());
    }
}
