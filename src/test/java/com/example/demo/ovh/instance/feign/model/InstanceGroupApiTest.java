package com.example.demo.ovh.instance.feign.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class InstanceGroupApiTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        InstanceGroupApi model = new InstanceGroupApi();
        model.setId("id");

        Assertions.assertEquals("id", model.getId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        InstanceGroupApi model = new InstanceGroupApi();
        model.setName("name");

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasRegionThenReturnRegion() {

        InstanceGroupApi model = new InstanceGroupApi();
        model.setRegion("region");

        Assertions.assertEquals("region", model.getRegion());
    }

    @Test
    public void whenModelHasTypeThenReturnType() {

        InstanceGroupApi model = new InstanceGroupApi();
        model.setType("type");

        Assertions.assertEquals("type", model.getType());
    }

    @Test
    public void whenModelHasInstanceIdsThenReturnInstanceIds() {

        List<String> instanceIds = Arrays.asList("id1", "id2");

        InstanceGroupApi model = new InstanceGroupApi();
        model.setInstanceIds(instanceIds);

        Assertions.assertEquals(instanceIds, model.getInstanceIds());
    }

    @Test
    public void whenModelToString() {

        InstanceGroupApi model = model();

        String expect = "InstanceGroupApi(id=id, name=name, instanceIds=[id1, id2], region=region, type=type)";

        Assertions.assertEquals(expect, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        InstanceGroupApi model = model();

        Assertions.assertEquals(-787723137, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        InstanceGroupApi model1 = model();
        InstanceGroupApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        InstanceGroupApi model = model();

        Assertions.assertNotEquals(model, new InstanceGroupApi());
    }

    private InstanceGroupApi model() {

        List<String> instanceIds = Arrays.asList("id1", "id2");

        InstanceGroupApi model = new InstanceGroupApi();
        model.setId("id");
        model.setName("name");
        model.setRegion("region");
        model.setType("type");
        model.setInstanceIds(instanceIds);

        return model;
    }
}
