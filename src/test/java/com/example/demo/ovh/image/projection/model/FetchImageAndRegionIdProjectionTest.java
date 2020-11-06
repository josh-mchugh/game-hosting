package com.example.demo.ovh.image.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchImageAndRegionIdProjectionTest {

    @Test
    public void whenProjectionHasIdThenReturnId() {

        FetchImageAndRegionIdProjection projection = projection();

        Assertions.assertEquals("id", projection.getId());
    }

    @Test
    public void whenProjectionHasRegionIdThenRegionId() {

        FetchImageAndRegionIdProjection projection = projection();

        Assertions.assertEquals("regionId", projection.getRegionId());
    }

    @Test
    public void whenProjectionToString() {

        FetchImageAndRegionIdProjection projection = projection();

        String toString = "FetchImageAndRegionIdProjection(id=id, regionId=regionId)";

        Assertions.assertEquals(toString, projection.toString());
    }

    @Test
    public void whenProjectionHashCode() {

        FetchImageAndRegionIdProjection projection = projection();

        Assertions.assertEquals(-690137599, projection.hashCode());
    }

    @Test
    public void whenProjectionEquals() {

        FetchImageAndRegionIdProjection projection1 = projection();
        FetchImageAndRegionIdProjection projection2 = projection();

        Assertions.assertEquals(projection1, projection2);
    }

    @Test
    public void whenProjectionNotEquals() {

        FetchImageAndRegionIdProjection projection = projection();

        Assertions.assertNotEquals(projection, new FetchImageAndRegionIdProjection("", ""));
    }

    private FetchImageAndRegionIdProjection projection() {

        return new FetchImageAndRegionIdProjection("id", "regionId");
    }
}
