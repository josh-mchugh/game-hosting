package com.example.demo.web.password.reset.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchUserIdByRecoveryTokenQueryTest {

    @Test
    public void whenModelHasTokenThenReturnToken() {

        FetchUserIdByRecoveryTokenQuery model = new FetchUserIdByRecoveryTokenQuery("token");

        Assertions.assertEquals("token", model.getToken());
    }

    @Test
    public void whenModelToString() {

        FetchUserIdByRecoveryTokenQuery model = model();

        String expected = "FetchUserIdByRecoveryTokenQuery(token=token)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchUserIdByRecoveryTokenQuery model = model();

        Assertions.assertEquals(110541364, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchUserIdByRecoveryTokenQuery model1 = model();
        FetchUserIdByRecoveryTokenQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchUserIdByRecoveryTokenQuery model = model();

        Assertions.assertNotEquals(model, new FetchUserIdByRecoveryTokenQuery(null));
    }

    private FetchUserIdByRecoveryTokenQuery model() {

        return new FetchUserIdByRecoveryTokenQuery("token");
    }
}
