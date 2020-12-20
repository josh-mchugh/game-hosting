package com.example.demo.ovh.region.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchRegionIdByNameQueryTest {

    @Test
    public void whenQueryHasNameThenReturnName() {

        FetchRegionIdByNameQuery query = new FetchRegionIdByNameQuery("name");

        Assertions.assertEquals("name", query.getName());
    }

    @Test
    public void whenQueryToString() {

        FetchRegionIdByNameQuery query = query();

        String expected = "FetchRegionIdByNameQuery(name=name)";

        Assertions.assertEquals(expected, query.toString());
    }

    @Test
    public void whenQueryHashCode() {

        FetchRegionIdByNameQuery query = query();

        Assertions.assertEquals(3373766, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        FetchRegionIdByNameQuery query1 = query();
        FetchRegionIdByNameQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        FetchRegionIdByNameQuery query = query();

        Assertions.assertNotEquals(query, new FetchRegionIdByNameQuery("diffName"));
    }

    private FetchRegionIdByNameQuery query() {

        return new FetchRegionIdByNameQuery("name");
    }
}
