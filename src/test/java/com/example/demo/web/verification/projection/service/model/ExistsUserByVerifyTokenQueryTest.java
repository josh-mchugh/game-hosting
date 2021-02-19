package com.example.demo.web.verification.projection.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsUserByVerifyTokenQueryTest {

    @Test
    public void whenModelHasTokenThenReturnToken() {

        ExistsUserByVerifyTokenQuery model = new ExistsUserByVerifyTokenQuery("token");

        Assertions.assertEquals("token", model.getToken());
    }

    @Test
    public void whenModelToString() {

        ExistsUserByVerifyTokenQuery model = model();

        String expected = "ExistsUserByVerifyTokenQuery(token=token)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsUserByVerifyTokenQuery model = model();

        Assertions.assertEquals(110541364, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsUserByVerifyTokenQuery model1 = model();
        ExistsUserByVerifyTokenQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsUserByVerifyTokenQuery model = model();

        Assertions.assertNotEquals(model, new ExistsUserByVerifyTokenQuery(null));
    }

    private ExistsUserByVerifyTokenQuery model() {

        return new ExistsUserByVerifyTokenQuery("token");
    }
}
