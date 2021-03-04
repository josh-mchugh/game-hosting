package com.example.demo.framework.seed.user.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsUserByEmailQueryTest {

    @Test
    public void whenModelHasEmailThenReturnEmail() {

        ExistsUserByEmailQuery model = new ExistsUserByEmailQuery("email");

        Assertions.assertEquals("email", model.getEmail());
    }

    @Test
    public void whenModelToString() {

        ExistsUserByEmailQuery model = model();

        String expected = "ExistsUserByEmailQuery(email=email)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsUserByEmailQuery model = model();

        Assertions.assertEquals(96619479, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsUserByEmailQuery model1 = model();
        ExistsUserByEmailQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsUserByEmailQuery model = model();

        Assertions.assertNotEquals(model, new ExistsUserByEmailQuery(null));
    }

    private ExistsUserByEmailQuery model() {

        return new ExistsUserByEmailQuery("email");
    }
}
