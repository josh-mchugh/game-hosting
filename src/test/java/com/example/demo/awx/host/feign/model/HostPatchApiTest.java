package com.example.demo.awx.host.feign.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HostPatchApiTest {

    @Test
    public void whenModelHasInventoryIdThenReturnInventoryId() {

        HostPatchApi model = HostPatchApi.builder()
                .inventoryId(1L)
                .build();

        Assertions.assertEquals(1L, model.getInventoryId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        HostPatchApi model = HostPatchApi.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        HostPatchApi model = HostPatchApi.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelHasEnabledThenReturnEnabled() {

        HostPatchApi model = HostPatchApi.builder()
                .enabled(true)
                .build();

        Assertions.assertTrue(model.getEnabled());
    }

    @Test
    public void whenModelToString() {

        HostPatchApi model = model();

        String expected = "HostPatchApi(inventoryId=1, name=name, description=description, enabled=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        HostPatchApi model = model();

        Assertions.assertEquals(-1508889488, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        HostPatchApi model1 = model();
        HostPatchApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        HostPatchApi model = model();

        Assertions.assertNotEquals(model, HostPatchApi.builder().build());
    }

    private HostPatchApi model() {

        return HostPatchApi.builder()
                .inventoryId(1L)
                .name("name")
                .description("description")
                .enabled(true)
                .build();
    }
}
