package com.example.demo.ovh.flavor.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchFlavorIdByOvhIdQueryTest {

    @Test
    public void whenQueryHasOvhIdThenReturnOvhId() {

        FetchFlavorIdByOvhIdQuery query = new FetchFlavorIdByOvhIdQuery("ovhId");

        Assertions.assertEquals("ovhId", query.getOvhId());
    }

    @Test
    public void whenQueryToString() {

        FetchFlavorIdByOvhIdQuery query = query();

        String expected = "FetchFlavorIdByOvhIdQuery(ovhId=ovhId)";

        Assertions.assertEquals(expected, query.toString());
    }

    @Test
    public void whenQueryHashCode() {

        FetchFlavorIdByOvhIdQuery query = query();

        Assertions.assertEquals(106128535, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        FetchFlavorIdByOvhIdQuery query1 = query();
        FetchFlavorIdByOvhIdQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        FetchFlavorIdByOvhIdQuery query = query();

        Assertions.assertNotEquals(query, new FetchFlavorIdByOvhIdQuery("diffId"));
    }

    private FetchFlavorIdByOvhIdQuery query() {

        return new FetchFlavorIdByOvhIdQuery("ovhId");
    }
}
