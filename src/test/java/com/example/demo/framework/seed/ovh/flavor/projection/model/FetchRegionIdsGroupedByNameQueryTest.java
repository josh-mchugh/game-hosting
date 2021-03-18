package com.example.demo.framework.seed.ovh.flavor.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchRegionIdsGroupedByNameQueryTest {

    @Test
    public void whenModelToString() {

        FetchRegionIdsGroupedByNameQuery model = model();

        String expected = "FetchRegionIdsGroupedByNameQuery()";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchRegionIdsGroupedByNameQuery model = model();

        Assertions.assertEquals(1, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchRegionIdsGroupedByNameQuery model1 = model();
        FetchRegionIdsGroupedByNameQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchRegionIdsGroupedByNameQuery model = model();

        Assertions.assertFalse(!model.equals(new FetchRegionIdsGroupedByNameQuery()));
    }

    private FetchRegionIdsGroupedByNameQuery model() {

        return new FetchRegionIdsGroupedByNameQuery();
    }
}
