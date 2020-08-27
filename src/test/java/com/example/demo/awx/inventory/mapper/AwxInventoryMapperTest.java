package com.example.demo.awx.inventory.mapper;

import com.example.demo.awx.inventory.entity.AwxInventoryEntity;
import com.example.demo.awx.inventory.model.AwxInventory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AwxInventoryMapperTest {

    @Test
    public void whenEntityIsNullThenReturnNull() {

        Assertions.assertNull(AwxInventoryMapper.map(null));
    }

    @Test
    public void whenEntityIsNotNullThenReturnNotNull() {

        Assertions.assertNotNull(AwxInventoryMapper.map(new AwxInventoryEntity()));
    }

    @Test
    public void whenEntityHasIdThenReturnId() {

        AwxInventoryEntity entity = new AwxInventoryEntity();
        entity.setId("id");

        AwxInventory awxInventory = AwxInventoryMapper.map(entity);

        Assertions.assertEquals("id", awxInventory.getId());
    }

    @Test
    public void whenEntityHasNullIdThenReturnNull() {

        AwxInventory awxInventory = AwxInventoryMapper.map(new AwxInventoryEntity());

        Assertions.assertNull(awxInventory.getId());
    }

    @Test
    public void whenEntityHasInventoryIdThenReturnInventoryId() {

        AwxInventoryEntity entity = new AwxInventoryEntity();
        entity.setInventoryId(1L);

        AwxInventory awxInventory = AwxInventoryMapper.map(entity);

        Assertions.assertEquals(1L, awxInventory.getInventoryId());
    }

    @Test
    public void whenEntityHasNullInventoryIdThenReturnNull() {

        AwxInventory awxInventory = AwxInventoryMapper.map(new AwxInventoryEntity());

        Assertions.assertNull(awxInventory.getInventoryId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        AwxInventoryEntity entity = new AwxInventoryEntity();
        entity.setName("name");

        AwxInventory awxInventory = AwxInventoryMapper.map(entity);

        Assertions.assertEquals("name", awxInventory.getName());
    }

    @Test
    public void whenEntityHasNullNameThenReturnNull() {

        AwxInventory awxInventory = AwxInventoryMapper.map(new AwxInventoryEntity());

        Assertions.assertNull(awxInventory.getName());
    }

    @Test
    public void whenEntityHasDescriptionThenReturnDescription() {

        AwxInventoryEntity entity = new AwxInventoryEntity();
        entity.setDescription("description");

        AwxInventory awxInventory = AwxInventoryMapper.map(entity);

        Assertions.assertEquals("description", awxInventory.getDescription());
    }

    @Test
    public void whenEntityHasNullDescriptionThenReturnNull() {

        AwxInventory awxInventory = AwxInventoryMapper.map(new AwxInventoryEntity());

        Assertions.assertNull(awxInventory.getDescription());
    }
}
