package com.example.demo.framework.seed.game.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsAnyGamesResponseTest {

    @Test
    public void whenModelHasExistThenReturnExists() {

        ExistsAnyGamesResponse model = new ExistsAnyGamesResponse(false);

        Assertions.assertFalse(model.exists());
    }

    @Test
    public void whenModelToString() {

        ExistsAnyGamesResponse model = model();

        String expected = "ExistsAnyGamesResponse(exists=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsAnyGamesResponse model = model();

        Assertions.assertEquals(138, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsAnyGamesResponse model1 = model();
        ExistsAnyGamesResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsAnyGamesResponse model = model();

        Assertions.assertNotEquals(model, new ExistsAnyGamesResponse(false));
    }

    private ExistsAnyGamesResponse model() {

        return new ExistsAnyGamesResponse(true);
    }
}
