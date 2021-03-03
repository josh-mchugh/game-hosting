package com.example.demo.framework.security.user.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchUserDetailsByEmailQueryTest {

    @Test
    public void whenModelHasEmailThenReturnEmail() {

        FetchUserDetailsByEmailQuery model = new FetchUserDetailsByEmailQuery("email");

        Assertions.assertEquals("email", model.getEmail());
    }

    @Test
    public void whenModelToString() {

        FetchUserDetailsByEmailQuery model = model();

        String expected = "FetchUserDetailsByEmailQuery(email=email)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchUserDetailsByEmailQuery model = model();

        Assertions.assertEquals(96619479, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchUserDetailsByEmailQuery model1 = model();
        FetchUserDetailsByEmailQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchUserDetailsByEmailQuery model = model();

        Assertions.assertNotEquals(model, new FetchUserDetailsByEmailQuery(null));
    }

    private FetchUserDetailsByEmailQuery model() {

        return new FetchUserDetailsByEmailQuery("email");
    }
}
