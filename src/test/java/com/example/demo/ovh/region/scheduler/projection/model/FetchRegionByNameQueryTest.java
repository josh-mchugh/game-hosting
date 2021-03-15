package com.example.demo.ovh.region.scheduler.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchRegionByNameQueryTest {

    @Test
    public void whenModelHasNameThenReturnName() {

        FetchRegionByNameQuery model = new FetchRegionByNameQuery("name");

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelToString() {

        FetchRegionByNameQuery model = model();

        String expected = "FetchRegionByNameQuery(name=name)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchRegionByNameQuery model = model();

        Assertions.assertEquals(3373766, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchRegionByNameQuery model1 = model();
        FetchRegionByNameQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchRegionByNameQuery model = model();

        Assertions.assertNotEquals(model, new FetchRegionByNameQuery(null));
    }

    private FetchRegionByNameQuery model() {

        return new FetchRegionByNameQuery("name");
    }
}
