package com.example.demo.ovh.image.scheduler.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsImageByNameAndRegionNameQueryTest {

    @Test
    public void whenModelHasNameThenReturnName() {

        ExistsImageByNameAndRegionNameQuery model = new ExistsImageByNameAndRegionNameQuery("name", null);

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasRegionNameThenReturnRegionName() {

        ExistsImageByNameAndRegionNameQuery model = new ExistsImageByNameAndRegionNameQuery(null, "regionName");

        Assertions.assertEquals("regionName", model.getRegionName());
    }

    @Test
    public void whenModelToString() {

        ExistsImageByNameAndRegionNameQuery model = model();

        String expected = "ExistsImageByNameAndRegionNameQuery(name=name, regionName=regionName)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsImageByNameAndRegionNameQuery model = model();

        Assertions.assertEquals(-1791637695, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsImageByNameAndRegionNameQuery model1 = model();
        ExistsImageByNameAndRegionNameQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsImageByNameAndRegionNameQuery model = model();

        Assertions.assertNotEquals(model, new ExistsImageByNameAndRegionNameQuery(null, null));
    }

    private ExistsImageByNameAndRegionNameQuery model() {

        return new ExistsImageByNameAndRegionNameQuery("name", "regionName");
    }
}
