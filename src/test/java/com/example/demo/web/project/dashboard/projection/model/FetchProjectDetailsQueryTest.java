package com.example.demo.web.project.dashboard.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchProjectDetailsQueryTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        FetchProjectDetailsQuery model = new FetchProjectDetailsQuery("id");

        Assertions.assertEquals("id", model.getId());
    }

    @Test
    public void whenModelToString() {

        FetchProjectDetailsQuery model = model();

        String expected = "FetchProjectDetailsQuery(id=id)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchProjectDetailsQuery model = model();

        Assertions.assertEquals(3414, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchProjectDetailsQuery model1 = model();
        FetchProjectDetailsQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchProjectDetailsQuery model = model();

        Assertions.assertNotEquals(model, new FetchProjectDetailsQuery(null));
    }

    private FetchProjectDetailsQuery model() {

        return new FetchProjectDetailsQuery("id");
    }
}
