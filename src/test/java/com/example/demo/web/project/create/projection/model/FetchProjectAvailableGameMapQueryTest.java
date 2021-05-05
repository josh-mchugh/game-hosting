package com.example.demo.web.project.create.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchProjectAvailableGameMapQueryTest {

    @Test
    public void whenModelToString() {

        FetchProjectAvailableGameMapQuery model = model();

        String expected = "FetchProjectAvailableGameMapQuery()";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchProjectAvailableGameMapQuery model = model();

        Assertions.assertEquals(1, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchProjectAvailableGameMapQuery model1 = model();
        FetchProjectAvailableGameMapQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchProjectAvailableGameMapQuery model = model();

        Assertions.assertFalse(!model.equals(new FetchProjectAvailableGameMapQuery()));
    }

    private FetchProjectAvailableGameMapQuery model() {

        return new FetchProjectAvailableGameMapQuery();
    }
}
