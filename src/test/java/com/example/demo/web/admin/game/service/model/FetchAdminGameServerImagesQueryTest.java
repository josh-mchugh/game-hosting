package com.example.demo.web.admin.game.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchAdminGameServerImagesQueryTest {

    @Test
    public void whenQueryHasSearchThenReturnSearch() {

        FetchAdminGameServerImagesQuery query = new FetchAdminGameServerImagesQuery("search", null);

        Assertions.assertEquals("search", query.getSearch());
    }

    @Test
    public void whenQueryHasRegionIdThenReturnRegionId() {

        FetchAdminGameServerImagesQuery query = new FetchAdminGameServerImagesQuery(null, "regionId");

        Assertions.assertEquals("regionId", query.getRegionId());
    }

    @Test
    public void whenQueryToString() {

        FetchAdminGameServerImagesQuery query = query();

        String expected = "FetchAdminGameServerImagesQuery(search=search, regionId=regionId)";

        Assertions.assertEquals(expected, query.toString());
    }

    @Test
    public void whenQueryHashCode() {

        FetchAdminGameServerImagesQuery query = query();

        Assertions.assertEquals(1670364800, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        FetchAdminGameServerImagesQuery query1 = query();
        FetchAdminGameServerImagesQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        FetchAdminGameServerImagesQuery query = query();

        Assertions.assertNotEquals(query, new FetchAdminGameServerImagesQuery(null, null));
    }

    private FetchAdminGameServerImagesQuery query() {

        return new FetchAdminGameServerImagesQuery("search", "regionId");
    }
}
