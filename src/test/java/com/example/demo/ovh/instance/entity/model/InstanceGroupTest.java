package com.example.demo.ovh.instance.entity.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InstanceGroupTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        InstanceGroup model = InstanceGroup.builder()
                .id("id")
                .build();

        Assertions.assertEquals("id", model.getId());
    }

    @Test
    public void whenModelHasGroupIdThenReturnGroupId() {

        InstanceGroup model = InstanceGroup.builder()
                .groupId("groupId")
                .build();

        Assertions.assertEquals("groupId", model.getGroupId());
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

        String expected = "InstanceGroup(id=id, groupId=groupId, name=name, type=type)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        InstanceGroup model = model();

        Assertions.assertEquals(125198639, model.hashCode());
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
                .id("id")
                .groupId("groupId")
                .name("name")
                .type("type")
                .build();
    }
}
