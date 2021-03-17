package com.example.demo.ovh.flavor.scheduler.projection.model;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchRegionIdsGroupedByNameResponseTest {

    @Test
    public void whenModelHasRegionsThenReturnRegions() {

        FetchRegionIdsGroupedByNameResponse model = new FetchRegionIdsGroupedByNameResponse(ImmutableMap.of("regionName", "regionId"));

        Assertions.assertEquals(ImmutableMap.of("regionName", "regionId"), model.getRegions());
    }

    @Test
    public void whenModelToString() {

        FetchRegionIdsGroupedByNameResponse model = model();

        String expected = "FetchRegionIdsGroupedByNameResponse(regions={regionName=regionId})";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchRegionIdsGroupedByNameResponse model = model();

        Assertions.assertEquals(1602369771, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchRegionIdsGroupedByNameResponse model1 = model();
        FetchRegionIdsGroupedByNameResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchRegionIdsGroupedByNameResponse model = model();

        Assertions.assertNotEquals(model, new FetchRegionIdsGroupedByNameResponse(null));
    }

    private FetchRegionIdsGroupedByNameResponse model() {

        return new FetchRegionIdsGroupedByNameResponse(ImmutableMap.of("regionName", "regionId"));
    }
}
