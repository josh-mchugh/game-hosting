package com.example.demo.web.project.create.query.model;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchProjectAvailableGameMapResponseTest {

    @Test
    public void whenModelHasAvailableGamesThenReturnAvailableGames() {

        FetchProjectAvailableGameMapResponse model = new FetchProjectAvailableGameMapResponse(ImmutableMap.of());

        Assertions.assertEquals(ImmutableMap.of(), model.getAvailableGames());
    }

    @Test
    public void whenModelToString() {

        FetchProjectAvailableGameMapResponse model = model();

        String expected = "FetchProjectAvailableGameMapResponse(availableGames={})";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchProjectAvailableGameMapResponse model = model();

        Assertions.assertEquals(59, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchProjectAvailableGameMapResponse model1 = model();
        FetchProjectAvailableGameMapResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchProjectAvailableGameMapResponse model = model();

        Assertions.assertNotEquals(model, new FetchProjectAvailableGameMapResponse(null));
    }

    private FetchProjectAvailableGameMapResponse model() {

        return new FetchProjectAvailableGameMapResponse(ImmutableMap.of());
    }
}
