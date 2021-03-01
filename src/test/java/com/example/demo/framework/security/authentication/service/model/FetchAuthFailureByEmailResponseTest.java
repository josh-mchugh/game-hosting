package com.example.demo.framework.security.authentication.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchAuthFailureByEmailResponseTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FetchAuthFailureByEmailResponse model = new FetchAuthFailureByEmailResponse(id);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelToString() {

        FetchAuthFailureByEmailResponse model = model();

        String expected = "FetchAuthFailureByEmailResponse(id=4174795c-cd85-4e04-a721-fbdc4fd1e4e3)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAuthFailureByEmailResponse model = model();

        Assertions.assertEquals(1677797538, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchAuthFailureByEmailResponse model1 = model();
        FetchAuthFailureByEmailResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchAuthFailureByEmailResponse model = model();

        Assertions.assertNotEquals(model, new FetchAuthFailureByEmailResponse(null));
    }

    private FetchAuthFailureByEmailResponse model() {

        return new FetchAuthFailureByEmailResponse(UUID.fromString("4174795c-cd85-4e04-a721-fbdc4fd1e4e3"));
    }
}
