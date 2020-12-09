package com.example.demo.awx.host.feign.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HostCreateApiTest {

    @Test
    public void whenModelHasInventoryIdThenReturnInventoryId() {

        HostCreateApi model = HostCreateApi.builder()
                .inventoryId(1L)
                .build();

        Assertions.assertEquals(1L, model.getInventoryId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        HostCreateApi model = HostCreateApi.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        HostCreateApi model = HostCreateApi.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelHasEnabledThenReturnEnabled() {

        HostCreateApi model = HostCreateApi.builder()
                .enabled(true)
                .build();

        Assertions.assertTrue(model.getEnabled());
    }

    @Test
    public void whenModelToString() {

        HostCreateApi model = model();

        String expected = "HostCreateApi(inventoryId=1, name=name, description=description, enabled=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        HostCreateApi model = model();

        Assertions.assertEquals(202294186, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        HostCreateApi model1 = model();
        HostCreateApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        HostCreateApi model = model();

        Assertions.assertNotEquals(model, HostCreateApi.builder().build());
    }

    private HostCreateApi model() {

        return HostCreateApi.builder()
                .inventoryId(1L)
                .name("name")
                .description("description")
                .enabled(true)
                .build();
    }
}
