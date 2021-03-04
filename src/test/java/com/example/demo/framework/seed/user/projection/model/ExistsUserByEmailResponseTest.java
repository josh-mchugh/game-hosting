package com.example.demo.framework.seed.user.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsUserByEmailResponseTest {

    @Test
    public void whenModelHasExistsThenReturnExists() {

        ExistsUserByEmailResponse model = new ExistsUserByEmailResponse(false);

        Assertions.assertFalse(model.exists());
    }

    @Test
    public void whenModelToString() {

        ExistsUserByEmailResponse model = model();

        String expected = "ExistsUserByEmailResponse(exists=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsUserByEmailResponse model = model();

        Assertions.assertEquals(138, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsUserByEmailResponse model1 = model();
        ExistsUserByEmailResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsUserByEmailResponse model = model();

        Assertions.assertNotEquals(model, new ExistsUserByEmailResponse(false));
    }

    private ExistsUserByEmailResponse model() {

        return new ExistsUserByEmailResponse(true);
    }
}
