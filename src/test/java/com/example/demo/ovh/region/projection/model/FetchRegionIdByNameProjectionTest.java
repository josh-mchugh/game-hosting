package com.example.demo.ovh.region.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchRegionIdByNameProjectionTest {

    @Test
    public void whenProjectionHasIdThenReturnId() {

        FetchRegionIdByNameProjection projection = new FetchRegionIdByNameProjection("id");

        Assertions.assertEquals("id", projection.getId());
    }

    @Test
    public void whenProjectionToString() {

        FetchRegionIdByNameProjection projection = projection();

        String expected = "FetchRegionIdByNameProjection(id=id)";

        Assertions.assertEquals(expected, projection.toString());
    }

    @Test
    public void whenProjectionHashCode() {

        FetchRegionIdByNameProjection projection = projection();

        Assertions.assertEquals(3414, projection.hashCode());
    }

    @Test
    public void whenProjectionEquals() {

        FetchRegionIdByNameProjection projection1 = projection();
        FetchRegionIdByNameProjection projection2 = projection();

        Assertions.assertEquals(projection1, projection2);
    }

    @Test
    public void whenProjectionNotEquals() {

        FetchRegionIdByNameProjection projection = projection();

        Assertions.assertNotEquals(projection, new FetchRegionIdByNameProjection("diffId"));
    }

    private FetchRegionIdByNameProjection projection() {

        return new FetchRegionIdByNameProjection("id");
    }
}
