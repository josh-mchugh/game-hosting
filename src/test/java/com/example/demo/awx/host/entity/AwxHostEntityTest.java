package com.example.demo.awx.host.entity;

import com.example.demo.awx.inventory.entity.AwxInventoryEntity;
import com.example.demo.ovh.instance.entity.InstanceEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class AwxHostEntityTest {

    @Test
    public void whenEntityHasIdThenReturnId() {

        AwxHostEntity entity = new AwxHostEntity();
        entity.setId("id");

        Assertions.assertEquals("id", entity.getId());
    }

    @Test
    public void whenEntityHasCreatedByThenReturnCreatedBy() {

        AwxHostEntity entity = new AwxHostEntity();
        entity.setCreatedBy("created-by");

        Assertions.assertEquals("created-by", entity.getCreatedBy());
    }

    @Test
    public void whenEntityHasCreatedDateThenCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        AwxHostEntity entity = new AwxHostEntity();
        entity.setCreatedDate(createdDate);

        Assertions.assertEquals(createdDate, entity.getCreatedDate());
    }

    @Test
    public void whenEntityHasLastModifiedByThenLastModifiedBy() {

        AwxHostEntity entity = new AwxHostEntity();
        entity.setLastModifiedBy("last-modified-by");

        Assertions.assertEquals("last-modified-by", entity.getLastModifiedBy());
    }

    @Test
    public void whenEntityHasLastModifiedDateThenLastModifiedDate() {

        LocalDateTime lastModifiedDate = LocalDateTime.now();

        AwxHostEntity entity = new AwxHostEntity();
        entity.setLastModifiedDate(lastModifiedDate);

        Assertions.assertEquals(lastModifiedDate, entity.getLastModifiedDate());
    }

    @Test
    public void whenEntityHasVersionThenReturnVersion() {

        AwxHostEntity entity = new AwxHostEntity();
        entity.setVersion(1L);

        Assertions.assertEquals(1L, entity.getVersion());
    }

    @Test
    public void whenEntityHasAwxInventoryEntityThenReturnAwxInventoryEntity() {

        AwxInventoryEntity awxInventoryEntity = new AwxInventoryEntity();

        AwxHostEntity entity = new AwxHostEntity();
        entity.setAwxInventoryEntity(awxInventoryEntity);

        Assertions.assertEquals(awxInventoryEntity, entity.getAwxInventoryEntity());
    }

    @Test
    public void whenEntityHasInstanceEntityThenReturnInstanceEntity() {

        InstanceEntity instanceEntity = new InstanceEntity();

        AwxHostEntity entity = new AwxHostEntity();
        entity.setInstanceEntity(instanceEntity);

        Assertions.assertEquals(instanceEntity, entity.getInstanceEntity());
    }

    @Test
    public void whenEntityHasAwxIdThenReturnAwxId() {

        AwxHostEntity entity = new AwxHostEntity();
        entity.setAwxId(1L);

        Assertions.assertEquals(1L, entity.getAwxId());
    }

    @Test
    public void whenEntityHasHostnameThenReturnHostname() {

        AwxHostEntity entity = new AwxHostEntity();
        entity.setHostname("hostname");

        Assertions.assertEquals("hostname", entity.getHostname());
    }

    @Test
    public void whenEntityHasDescriptionThenReturnDescription() {

        AwxHostEntity entity = new AwxHostEntity();
        entity.setDescription("description");

        Assertions.assertEquals("description", entity.getDescription());
    }

    @Test
    public void whenEntityHasEnabledThenReturnEnabled() {

        AwxHostEntity entity = new AwxHostEntity();
        entity.setEnabled(true);

        Assertions.assertEquals(true, entity.getEnabled());
    }
}
