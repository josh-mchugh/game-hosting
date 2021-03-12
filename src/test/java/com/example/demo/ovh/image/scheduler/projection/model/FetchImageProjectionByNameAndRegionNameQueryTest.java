package com.example.demo.ovh.image.scheduler.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchImageProjectionByNameAndRegionNameQueryTest {

    @Test
    public void whenModelHasNameThenReturnName() {

        FetchImageProjectionByNameAndRegionNameQuery model = new FetchImageProjectionByNameAndRegionNameQuery("name", null);

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasRegionNameThenReturnRegionName() {

        FetchImageProjectionByNameAndRegionNameQuery model = new FetchImageProjectionByNameAndRegionNameQuery(null, "regionName");

        Assertions.assertEquals("regionName", model.getRegionName());
    }

    @Test
    public void whenModelToString() {

        FetchImageProjectionByNameAndRegionNameQuery model = model();

        String expected = "FetchImageProjectionByNameAndRegionNameQuery(name=name, regionName=regionName)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchImageProjectionByNameAndRegionNameQuery model = model();

        Assertions.assertEquals(-1791637695, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchImageProjectionByNameAndRegionNameQuery model1 = model();
        FetchImageProjectionByNameAndRegionNameQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchImageProjectionByNameAndRegionNameQuery model = model();

        Assertions.assertNotEquals(model, new FetchImageProjectionByNameAndRegionNameQuery(null, null));
    }

    private FetchImageProjectionByNameAndRegionNameQuery model() {

        return new FetchImageProjectionByNameAndRegionNameQuery("name", "regionName");
    }
}
