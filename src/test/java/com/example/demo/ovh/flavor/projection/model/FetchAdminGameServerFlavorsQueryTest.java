package com.example.demo.ovh.flavor.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchAdminGameServerFlavorsQueryTest {

    @Test
    public void whenQueryHasSearchThenReturnSearch() {

        FetchAdminGameServerFlavorsQuery query = new FetchAdminGameServerFlavorsQuery("search", null);

        Assertions.assertEquals("search", query.getSearch());
    }

    @Test
    public void whenQueryHasRegionIdThenReturnRegionId() {

        FetchAdminGameServerFlavorsQuery query = new FetchAdminGameServerFlavorsQuery(null, "regionId");

        Assertions.assertEquals("regionId", query.getRegionId());
    }

    @Test
    public void whenQueryToString() {

        FetchAdminGameServerFlavorsQuery query = query();

        String expected = "FetchAdminGameServerFlavorsQuery(search=search, regionId=regionId)";

        Assertions.assertEquals(expected, query.toString());
    }

    @Test
    public void whenQueryHashCode() {

        FetchAdminGameServerFlavorsQuery query = query();

        Assertions.assertEquals(1670364800, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        FetchAdminGameServerFlavorsQuery query1 = query();
        FetchAdminGameServerFlavorsQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        FetchAdminGameServerFlavorsQuery query = query();

        Assertions.assertNotEquals(query, new FetchAdminGameServerFlavorsQuery(null, null));
    }

    private FetchAdminGameServerFlavorsQuery query() {

        return new FetchAdminGameServerFlavorsQuery("search", "regionId");
    }
}
