package com.example.demo.awx.inventory.entity.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxInventoryTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxInventory model = AwxInventory.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasAwxIdThenReturnAwxId() {

        AwxInventory model = AwxInventory.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, model.getAwxId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        AwxInventory model = AwxInventory.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        AwxInventory model = AwxInventory.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelToString() {

        AwxInventory model = model();

        String expected = "AwxInventory(id=6e17af83-95d3-443a-b095-cdf4b892cbcb, awxId=1, name=name, description=description)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AwxInventory model = model();

        Assertions.assertEquals(1224182991, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AwxInventory model1 = model();
        AwxInventory model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        AwxInventory model = model();

        Assertions.assertNotEquals(model, AwxInventory.builder().build());
    }

    private AwxInventory model() {

        return AwxInventory.builder()
                .id(UUID.fromString("6e17af83-95d3-443a-b095-cdf4b892cbcb"))
                .awxId(1L)
                .name("name")
                .description("description")
                .build();
    }
}
