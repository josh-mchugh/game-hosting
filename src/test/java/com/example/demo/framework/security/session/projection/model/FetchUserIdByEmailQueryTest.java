package com.example.demo.framework.security.session.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchUserIdByEmailQueryTest {

    @Test
    public void whenModelHasEmailThenReturnEmail() {

        FetchUserIdByEmailQuery query = new FetchUserIdByEmailQuery("email");

        Assertions.assertEquals("email", query.getEmail());
    }

    @Test
    public void whenModelToString() {

        FetchUserIdByEmailQuery model = model();

        String expected = "FetchUserIdByEmailQuery(email=email)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchUserIdByEmailQuery model = model();

        Assertions.assertEquals(96619479, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchUserIdByEmailQuery model1 = model();
        FetchUserIdByEmailQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchUserIdByEmailQuery model = model();

        Assertions.assertNotEquals(model , new FetchUserIdByEmailQuery(null));
    }

    private FetchUserIdByEmailQuery model() {

        return new FetchUserIdByEmailQuery("email");
    }
}
