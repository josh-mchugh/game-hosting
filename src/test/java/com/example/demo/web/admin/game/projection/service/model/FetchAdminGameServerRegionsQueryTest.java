package com.example.demo.web.admin.game.projection.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchAdminGameServerRegionsQueryTest {

    @Test
    public void whenQueryToString() {

        FetchAdminGameServerRegionsQuery model = query();

        String expected = "FetchAdminGameServerRegionsQuery()";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenQueryHashCode() {

        FetchAdminGameServerRegionsQuery query = query();

        Assertions.assertEquals(1, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        FetchAdminGameServerRegionsQuery query1 = query();
        FetchAdminGameServerRegionsQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        FetchAdminGameServerRegionsQuery query = query();

        Assertions.assertFalse(!query.equals(new FetchAdminGameServerRegionsQuery()));
    }

    private FetchAdminGameServerRegionsQuery query() {

        return new FetchAdminGameServerRegionsQuery();
    }
}
