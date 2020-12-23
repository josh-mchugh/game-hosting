package com.example.demo.ovh.region.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchRegionByNameQueryTest {

    @Test
    public void whenQueryHasNameThenReturnName() {

        FetchRegionByNameQuery query = new FetchRegionByNameQuery("name");

        Assertions.assertEquals("name", query.getName());
    }

    @Test
    public void whenQueryToString() {

        FetchRegionByNameQuery query = query();

        String expected = "FetchRegionByNameQuery(name=name)";

        Assertions.assertEquals(expected, query.toString());
    }

    @Test
    public void whenQueryHashCode() {

        FetchRegionByNameQuery query = query();

        Assertions.assertEquals(3373766, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        FetchRegionByNameQuery query1 = query();
        FetchRegionByNameQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        FetchRegionByNameQuery query = query();

        Assertions.assertNotEquals(query, new FetchRegionByNameQuery("diffName"));
    }

    private FetchRegionByNameQuery query() {

        return new FetchRegionByNameQuery("name");
    }
}
