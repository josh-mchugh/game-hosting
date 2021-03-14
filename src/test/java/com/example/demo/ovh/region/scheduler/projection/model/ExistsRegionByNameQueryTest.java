package com.example.demo.ovh.region.scheduler.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsRegionByNameQueryTest {

    @Test
    public void whenQueryHasNameThenReturnName() {

        ExistsRegionByNameQuery query = new ExistsRegionByNameQuery("name");

        Assertions.assertEquals("name", query.getName());
    }

    @Test
    public void whenQueryToString() {

        ExistsRegionByNameQuery query = query();

        String expected = "ExistsRegionByNameQuery(name=name)";

        Assertions.assertEquals(expected, query.toString());
    }

    @Test
    public void whenQueryHashCode() {

        ExistsRegionByNameQuery query = query();

        Assertions.assertEquals(3373766, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        ExistsRegionByNameQuery query1 = query();
        ExistsRegionByNameQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        ExistsRegionByNameQuery query = query();

        Assertions.assertNotEquals(query, new ExistsRegionByNameQuery("diffName"));
    }

    private ExistsRegionByNameQuery query() {

        return new ExistsRegionByNameQuery("name");
    }
}
