package com.example.demo.framework.seed.ovh.region.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsAnyRegionQueryTest {

    @Test
    public void whenModelToString() {

        ExistsAnyRegionQuery model = model();

        String expected = "ExistsAnyRegionQuery()";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsAnyRegionQuery model = model();

        Assertions.assertEquals(1, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsAnyRegionQuery model1 = model();
        ExistsAnyRegionQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsAnyRegionQuery model = model();

        Assertions.assertFalse(!model.equals(new ExistsAnyRegionQuery()));
    }

    private ExistsAnyRegionQuery model() {

        return new ExistsAnyRegionQuery();
    }
}
