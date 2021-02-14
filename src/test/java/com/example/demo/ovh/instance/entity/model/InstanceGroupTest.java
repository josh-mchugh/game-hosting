package com.example.demo.ovh.instance.entity.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class InstanceGroupTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        InstanceGroup model = InstanceGroup.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasOvhIdThenReturnOvhId() {

        InstanceGroup model = InstanceGroup.builder()
                .ovhId("ovhId")
                .build();

        Assertions.assertEquals("ovhId", model.getOvhId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        InstanceGroup model = InstanceGroup.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasTypeThenReturnName() {

        InstanceGroup model = InstanceGroup.builder()
                .type("type")
                .build();

        Assertions.assertEquals("type", model.getType());
    }

    @Test
    public void whenModelToString() {

        InstanceGroup model = model();

        String expected = "InstanceGroup(id=5810a4e0-8b42-4101-a38e-77c22c1376bc, ovhId=ovhId, name=name, type=type)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        InstanceGroup model = model();

        Assertions.assertEquals(-1520480947, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        InstanceGroup model1 = model();
        InstanceGroup model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        InstanceGroup model = model();

        Assertions.assertNotEquals(model, InstanceGroup.builder().build());
    }

    private InstanceGroup model() {

        return InstanceGroup.builder()
                .id(UUID.fromString("5810a4e0-8b42-4101-a38e-77c22c1376bc"))
                .ovhId("ovhId")
                .name("name")
                .type("type")
                .build();
    }
}
