package com.example.demo.game.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class FetchAdminGameServerGamesResponseTest {

    @Test
    public void whenModelHasGamesThenReturnGames() {

        FetchAdminGameServerGamesResponse model = new FetchAdminGameServerGamesResponse(new ArrayList<>());

        Assertions.assertEquals(new ArrayList<>(), model.getGames());
    }

    @Test
    public void whenModelToString() {

        FetchAdminGameServerGamesResponse model = model();

        String expected = "FetchAdminGameServerGamesResponse(games=[])";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAdminGameServerGamesResponse model = model();

        Assertions.assertEquals(60, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchAdminGameServerGamesResponse model1 = model();
        FetchAdminGameServerGamesResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchAdminGameServerGamesResponse model = model();

        Assertions.assertNotEquals(model, new FetchAdminGameServerGamesResponse(null));
    }

    private FetchAdminGameServerGamesResponse model() {

        return new FetchAdminGameServerGamesResponse(new ArrayList<>());
    }
}
