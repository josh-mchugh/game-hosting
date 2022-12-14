package com.example.demo.ovh.region.scheduler.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsRegionByNameQueryTest {

    @Test
    public void whenModelHasNameThenReturnName() {

        ExistsRegionByNameQuery model = new ExistsRegionByNameQuery("name");

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelToString() {

        ExistsRegionByNameQuery model = model();

        String expected = "ExistsRegionByNameQuery(name=name)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsRegionByNameQuery model = model();

        Assertions.assertEquals(3373766, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsRegionByNameQuery model1 = model();
        ExistsRegionByNameQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsRegionByNameQuery model = model();

        Assertions.assertNotEquals(model, new ExistsRegionByNameQuery(null));
    }

    private ExistsRegionByNameQuery model() {

        return new ExistsRegionByNameQuery("name");
    }
}
