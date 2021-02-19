package com.example.demo.web.verification.projection.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsUserByVerifyTokenResponseTest {

    @Test
    public void whenModelHasExistThenReturnExist() {

        ExistsUserByVerifyTokenResponse model = new ExistsUserByVerifyTokenResponse(true);

        Assertions.assertTrue(model.isExist());
    }

    @Test
    public void whenModelToString() {

        ExistsUserByVerifyTokenResponse model = model();

        String expected = "ExistsUserByVerifyTokenResponse(exist=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsUserByVerifyTokenResponse model = model();

        Assertions.assertEquals(138, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsUserByVerifyTokenResponse model1 = model();
        ExistsUserByVerifyTokenResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsUserByVerifyTokenResponse model = model();

        Assertions.assertNotEquals(model, new ExistsUserByVerifyTokenResponse(false));
    }

    private ExistsUserByVerifyTokenResponse model() {

        return new ExistsUserByVerifyTokenResponse(true);
    }
}
