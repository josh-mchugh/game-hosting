package com.example.demo.ovh.flavor.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchFlavorIdByOvhIdProjectionTest {

    @Test
    public void whenProjectionHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FetchFlavorIdByOvhIdProjection projection = new FetchFlavorIdByOvhIdProjection(id.toString());

        Assertions.assertEquals(id, projection.getId());
    }

    @Test
    public void whenProjectionToString() {

        FetchFlavorIdByOvhIdProjection projection = projection();

        String expected = "FetchFlavorIdByOvhIdProjection(id=db97458e-da04-4ab5-bdd1-f1da66fffabc)";

        Assertions.assertEquals(expected, projection.toString());
    }

    @Test
    public void whenProjectionHashCode() {

        FetchFlavorIdByOvhIdProjection projection = projection();

        Assertions.assertEquals(-625146728, projection.hashCode());
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

        Assertions.assertNotEquals(projection, new FetchFlavorIdByOvhIdProjection(UUID.randomUUID().toString()));
    }

    private FetchFlavorIdByOvhIdProjection projection() {

        return new FetchFlavorIdByOvhIdProjection(UUID.fromString("db97458e-da04-4ab5-bdd1-f1da66fffabc").toString());
    }
}
