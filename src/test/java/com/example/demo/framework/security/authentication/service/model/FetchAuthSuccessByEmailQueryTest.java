package com.example.demo.framework.security.authentication.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchAuthSuccessByEmailQueryTest {

    @Test
    public void whenModelHasEmailThenReturnEmail() {

        FetchAuthSuccessByEmailQuery model = new FetchAuthSuccessByEmailQuery("email");

        Assertions.assertEquals("email", model.getEmail());
    }

    @Test
    public void whenModelToString() {

        FetchAuthSuccessByEmailQuery model = model();

        String expected = "FetchAuthSuccessByEmailQuery(email=email)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAuthSuccessByEmailQuery model = model();

        Assertions.assertEquals(96619479, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchAuthSuccessByEmailQuery model1 = model();
        FetchAuthSuccessByEmailQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchAuthSuccessByEmailQuery model = model();

        Assertions.assertNotEquals(model, new FetchAuthSuccessByEmailQuery(null));
    }

    private FetchAuthSuccessByEmailQuery model() {

        return new FetchAuthSuccessByEmailQuery("email");
    }
}
