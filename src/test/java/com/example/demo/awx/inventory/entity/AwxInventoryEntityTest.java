package com.example.demo.awx.inventory.entity;

import com.example.demo.awx.host.entity.AwxHostEntity;
import com.example.demo.awx.organization.entity.AwxOrganizationEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class AwxInventoryEntityTest {

    @Test
    public void whenEntityHasIdThenReturnId() {

        AwxInventoryEntity entity = new AwxInventoryEntity();
        entity.setId("id");

        Assertions.assertEquals("id", entity.getId());
    }

    @Test
    public void whenEntityHasCreatedByThenReturnCreatedBy() {

        AwxInventoryEntity entity = new AwxInventoryEntity();
        entity.setCreatedBy("created-by");

        Assertions.assertEquals("created-by", entity.getCreatedBy());
    }

    @Test
    public void whenEntityHasCreatedDateThenCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        AwxInventoryEntity entity = new AwxInventoryEntity();
        entity.setCreatedDate(createdDate);

        Assertions.assertEquals(createdDate, entity.getCreatedDate());
    }

    @Test
    public void whenEntityHasLastModifiedByThenLastModifiedBy() {

        AwxInventoryEntity entity = new AwxInventoryEntity();
        entity.setLastModifiedBy("last-modified-by");

        Assertions.assertEquals("last-modified-by", entity.getLastModifiedBy());
    }

    @Test
    public void whenEntityHasLastModifiedDateThenLastModifiedDate() {

        LocalDateTime lastModifiedDate = LocalDateTime.now();

        AwxInventoryEntity entity = new AwxInventoryEntity();
        entity.setLastModifiedDate(lastModifiedDate);

        Assertions.assertEquals(lastModifiedDate, entity.getLastModifiedDate());
    }

    @Test
    public void whenEntityHasVersionThenReturnVersion() {

        AwxInventoryEntity entity = new AwxInventoryEntity();
        entity.setVersion(1L);

        Assertions.assertEquals(1L, entity.getVersion());
    }

    @Test
    public void whenEntityHasAwxOrganizationEntityThenReturnAwxOrganizationEntity() {

        AwxOrganizationEntity awxOrganizationEntity = new AwxOrganizationEntity();

        AwxInventoryEntity entity = new AwxInventoryEntity();
        entity.setAwxOrganizationEntity(awxOrganizationEntity);

        Assertions.assertEquals(awxOrganizationEntity, entity.getAwxOrganizationEntity());
    }

    @Test
    public void whenEntityHasAwxIdThenReturnAwxId() {

        AwxInventoryEntity entity = new AwxInventoryEntity();
        entity.setAwxId(1L);

        Assertions.assertEquals(1L, entity.getAwxId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        AwxInventoryEntity entity = new AwxInventoryEntity();
        entity.setName("name");

        Assertions.assertEquals("name", entity.getName());
    }

    @Test
    public void whenEntityHasDescriptionThenReturnDescription() {

        AwxInventoryEntity entity = new AwxInventoryEntity();
        entity.setDescription("description");

        Assertions.assertEquals("description", entity.getDescription());
    }

    @Test
    public void whenEntityHasAwxHostEntitiesThenReturnAwxHostEntities() {

        List<AwxHostEntity> awxHostEntities = Arrays.asList(new AwxHostEntity(), new AwxHostEntity());

        AwxInventoryEntity entity = new AwxInventoryEntity();
        entity.setAwxHostEntities(awxHostEntities);

        Assertions.assertEquals(awxHostEntities, entity.getAwxHostEntities());
    }
}
