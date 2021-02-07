package com.example.demo.game.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchAdminGameServerGamesQueryTest {

    @Test
    public void whenQueryToString() {

        FetchAdminGameServerGamesQuery query = query();

        String expected = "FetchAdminGameServerGamesQuery()";

        Assertions.assertEquals(expected, query.toString());
    }

    @Test
    public void whenQueryHashCode() {

        FetchAdminGameServerGamesQuery query = query();

        Assertions.assertEquals(1, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        FetchAdminGameServerGamesQuery query1 = query();
        FetchAdminGameServerGamesQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        FetchAdminGameServerGamesQuery query = query();

        // Refactor once model object has more variables
        Assertions.assertFalse(!query.equals(new FetchAdminGameServerGamesQuery()));
    }

    private FetchAdminGameServerGamesQuery query() {

        return new FetchAdminGameServerGamesQuery();
    }
}
