package com.example.demo.awx.inventory.feign.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InventoryCreateApiTest {

    @Test
    public void whenModelHasNameThenReturnName() {

        InventoryCreateApi model = InventoryCreateApi.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        InventoryCreateApi model = InventoryCreateApi.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelHasOrganizationIdThenReturnOrganizationId() {

        InventoryCreateApi model = InventoryCreateApi.builder()
                .organizationId(1L)
                .build();

        Assertions.assertEquals(1L, model.getOrganizationId());
    }

    @Test
    public void whenModelToString() {

        InventoryCreateApi model = model();

        String expected = "InventoryCreateApi(name=name, description=description, organizationId=1)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        InventoryCreateApi model = model();

        Assertions.assertEquals(-1525288479, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        InventoryCreateApi model1 = model();
        InventoryCreateApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        InventoryCreateApi model = model();

        Assertions.assertNotEquals(model, InventoryCreateApi.builder().build());
    }

    private InventoryCreateApi model() {

        return InventoryCreateApi.builder()
                .name("name")
                .description("description")
                .organizationId(1L)
                .build();
    }
}
