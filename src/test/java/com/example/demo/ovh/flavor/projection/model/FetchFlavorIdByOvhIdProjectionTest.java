package com.example.demo.ovh.flavor.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchFlavorIdByOvhIdProjectionTest {

    @Test
    public void whenProjectionHasIdThenReturnId() {

        FetchFlavorIdByOvhIdProjection projection = new FetchFlavorIdByOvhIdProjection("id");

        Assertions.assertEquals("id", projection.getId());
    }

    @Test
    public void whenProjectionToString() {

        FetchFlavorIdByOvhIdProjection projection = projection();

        String expected = "FetchFlavorIdByOvhIdProjection(id=id)";

        Assertions.assertEquals(expected, projection.toString());
    }

    @Test
    public void whenProjectionHashCode() {

        FetchFlavorIdByOvhIdProjection projection = projection();

        Assertions.assertEquals(3414, projection.hashCode());
    }

    @Test
    public void whenProjectionEquals() {

        FetchFlavorIdByOvhIdProjection projection1 = projection();
        FetchFlavorIdByOvhIdProjection projection2 = projection();

        Assertions.assertEquals(projection1, projection2);
    }

    @Test
    public void whenProjectionNotEquals() {

        FetchFlavorIdByOvhIdProjection projection = projection();

        Assertions.assertNotEquals(projection, new FetchFlavorIdByOvhIdProjection("diffId"));
    }

    private FetchFlavorIdByOvhIdProjection projection() {

        return new FetchFlavorIdByOvhIdProjection("id");
    }
}
