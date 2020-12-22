package com.example.demo.ovh.region.projection.model;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchRegionIdsGroupedByNameProjectionTest {

    @Test
    public void whenProjectionHasRegionMapThenReturnRegionMap() {

        FetchRegionIdsGroupByNameProjection projection = new FetchRegionIdsGroupByNameProjection(ImmutableMap.of("name", "id"));

        Assertions.assertEquals(ImmutableMap.of("name", "id"), projection.getRegionMap());
    }

    @Test
    public void whenProjectionToString() {

        FetchRegionIdsGroupByNameProjection projection = projection();

        String expected = "FetchRegionIdsGroupByNameProjection(regionMap={name=id})";

        Assertions.assertEquals(expected, projection.toString());
    }

    @Test
    public void whenProjectionHashCode() {

        FetchRegionIdsGroupByNameProjection projection = projection();

        Assertions.assertEquals(3373003, projection.hashCode());
    }

    @Test
    public void whenProjectionEquals() {

        FetchRegionIdsGroupByNameProjection projection1 = projection();
        FetchRegionIdsGroupByNameProjection projection2 = projection();

        Assertions.assertEquals(projection1, projection2);
    }

    @Test
    public void whenProjectionNotEquals() {

        FetchRegionIdsGroupByNameProjection projection = projection();

        Assertions.assertNotEquals(projection, new FetchRegionIdsGroupByNameProjection(ImmutableMap.of("diffName", "diffId")));
    }

    private FetchRegionIdsGroupByNameProjection projection() {

        return new FetchRegionIdsGroupByNameProjection(ImmutableMap.of("name", "id"));
    }
}
