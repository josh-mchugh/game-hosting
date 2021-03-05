package com.example.demo.framework.seed.game.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsAnyGamesQueryTest {

    @Test
    public void whenModelToString() {

        ExistsAnyGamesQuery model = model();

        String expected = "ExistsAnyGamesQuery()";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsAnyGamesQuery model = model();

        Assertions.assertEquals(1, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsAnyGamesQuery model1 = model();
        ExistsAnyGamesQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsAnyGamesQuery model = model();

        Assertions.assertFalse(!model.equals(new ExistsAnyGamesQuery()));
    }

    private ExistsAnyGamesQuery model() {

        return new ExistsAnyGamesQuery();
    }
}
