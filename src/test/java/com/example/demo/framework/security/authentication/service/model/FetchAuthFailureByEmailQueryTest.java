package com.example.demo.framework.security.authentication.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchAuthFailureByEmailQueryTest {

    @Test
    public void whenModelHasEmailThenReturnEmail() {

        FetchAuthFailureByEmailQuery model = new FetchAuthFailureByEmailQuery("email");

        Assertions.assertEquals("email", model.getEmail());
    }

    @Test
    public void whenModelToString() {

        FetchAuthFailureByEmailQuery model = model();

        String expected = "FetchAuthFailureByEmailQuery(email=email)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAuthFailureByEmailQuery model = model();

        Assertions.assertEquals(96619479, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchAuthFailureByEmailQuery model1 = model();
        FetchAuthFailureByEmailQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchAuthFailureByEmailQuery model = model();

        Assertions.assertNotEquals(model, new FetchAuthFailureByEmailQuery(null));
    }

    private FetchAuthFailureByEmailQuery model() {

        return new FetchAuthFailureByEmailQuery("email");
    }
}
