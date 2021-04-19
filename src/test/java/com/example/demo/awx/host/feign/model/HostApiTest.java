package com.example.demo.awx.host.feign.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HostApiTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        HostApi model = new HostApi();
        model.setId(1L);

        Assertions.assertEquals(1L, model.getId());
    }

    @Test
    public void whenModelHasInventoryIdThenReturnInventoryId() {

        HostApi model = new HostApi();
        model.setInventoryId(1L);

        Assertions.assertEquals(1L, model.getInventoryId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        HostApi model = new HostApi();
        model.setName("name");

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasEnabledThenReturnEnabled() {

        HostApi model = new HostApi();
        model.setEnabled(true);

        Assertions.assertTrue(model.getEnabled());
    }

    @Test
    public void whenModelToString() {

        HostApi model = model();

        String expected = "HostApi(id=1, inventoryId=1, name=name, description=description, enabled=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        HostApi model = model();

        Assertions.assertEquals(-793965189, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        HostApi model1 = model();
        HostApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        HostApi model = model();

        Assertions.assertNotEquals(model, new HostApi());
    }

    private HostApi model() {

        HostApi model = new HostApi();
        model.setId(1L);
        model.setInventoryId(1L);
        model.setName("name");
        model.setDescription("description");
        model.setEnabled(true);

        return model;
    }
}
