package com.example.demo.ovh.instance.feign.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InstanceGroupCreateApiTest {

    @Test
    public void whenModelHasNameThenReturnName() {

        InstanceGroupCreateApi model = InstanceGroupCreateApi.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasRegionThenReturnName() {

        InstanceGroupCreateApi model = InstanceGroupCreateApi.builder()
                .region("region")
                .build();

        Assertions.assertEquals("region", model.getRegion());
    }

    @Test
    public void whenModelHasTypeThenReturnType() {

        InstanceGroupCreateApi model = InstanceGroupCreateApi.builder()
                .type("type")
                .build();

        Assertions.assertEquals("type", model.getType());
    }

    @Test
    public void whenModelToString() {

        InstanceGroupCreateApi model = model();

        String expected = "InstanceGroupCreateApi(name=name, region=region, type=type)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        InstanceGroupCreateApi model = model();

        Assertions.assertEquals(-455608372, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        InstanceGroupCreateApi model1 = model();
        InstanceGroupCreateApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        InstanceGroupCreateApi model = model();

        Assertions.assertNotEquals(model, InstanceGroupCreateApi.builder().build());
    }

    private InstanceGroupCreateApi model() {

        return InstanceGroupCreateApi.builder()
                .name("name")
                .region("region")
                .type("type")
                .build();
    }
}
