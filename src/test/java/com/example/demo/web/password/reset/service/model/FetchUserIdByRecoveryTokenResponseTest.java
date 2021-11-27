package com.example.demo.web.password.reset.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchUserIdByRecoveryTokenResponseTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FetchUserIdByRecoveryTokenResponse model = new FetchUserIdByRecoveryTokenResponse(id);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelToString() {

        FetchUserIdByRecoveryTokenResponse model = model();

        String expected = "FetchUserIdByRecoveryTokenResponse(id=73eeb0c2-ffd0-4930-b1ce-69f11e64725b)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchUserIdByRecoveryTokenResponse model = model();

        Assertions.assertEquals(596959891, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchUserIdByRecoveryTokenResponse model1 = model();
        FetchUserIdByRecoveryTokenResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchUserIdByRecoveryTokenResponse model = model();

        Assertions.assertNotEquals(model, new FetchUserIdByRecoveryTokenResponse(UUID.randomUUID()));
    }

    private FetchUserIdByRecoveryTokenResponse model() {

        return new FetchUserIdByRecoveryTokenResponse(UUID.fromString("73eeb0c2-ffd0-4930-b1ce-69f11e64725b"));
    }
}
