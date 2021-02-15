package com.example.demo.web.admin.game.projection.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchAdminGameServerGamesQueryTest {

    @Test
    public void whenModelToString() {

        FetchAdminGameServerGamesQuery model = model();

        String expected = "FetchAdminGameServerGamesQuery()";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAdminGameServerGamesQuery model = model();

        Assertions.assertEquals(1, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchAdminGameServerGamesQuery model1 = model();
        FetchAdminGameServerGamesQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchAdminGameServerGamesQuery model = model();

        Assertions.assertFalse(!model.equals(new FetchAdminGameServerGamesQuery()));
    }

    private FetchAdminGameServerGamesQuery model() {

        return new FetchAdminGameServerGamesQuery();
    }
}
