package com.example.demo.awx.inventory.feign.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InventoryApiTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        InventoryApi model = new InventoryApi();
        model.setId(1L);

        Assertions.assertEquals(1L, model.getId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        InventoryApi model = new InventoryApi();
        model.setName("name");

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionTheReturnDescription() {

        InventoryApi model = new InventoryApi();
        model.setDescription("description");

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelHasOrganizationIdThenReturnOrganizationId() {

        InventoryApi model = new InventoryApi();
        model.setOrganizationId(1L);

        Assertions.assertEquals(1L, model.getOrganizationId());
    }

    @Test
    public void whenModelToString() {

        InventoryApi model = model();

        String expected = "InventoryApi(id=1, name=name, description=description, organizationId=1)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        InventoryApi model = model();

        Assertions.assertEquals(-1513171118, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        InventoryApi model1 = model();
        InventoryApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        InventoryApi model = model();

        Assertions.assertNotEquals(model, new InventoryApi());
    }

    private InventoryApi model() {

        InventoryApi model = new InventoryApi();
        model.setId(1L);
        model.setName("name");
        model.setDescription("description");
        model.setOrganizationId(1L);

        return model;
    }
}
