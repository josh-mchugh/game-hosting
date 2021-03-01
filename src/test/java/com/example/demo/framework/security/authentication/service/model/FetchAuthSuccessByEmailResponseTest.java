package com.example.demo.framework.security.authentication.service.model;

import com.example.demo.framework.security.authentication.service.projection.AuthSuccessProjection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchAuthSuccessByEmailResponseTest {

    @Test
    public void whenModelHasUserThenReturnUser() {

        UUID id = UUID.randomUUID();

        FetchAuthSuccessByEmailResponse model = new FetchAuthSuccessByEmailResponse(new AuthSuccessProjection(id.toString(), false));

        Assertions.assertEquals(new AuthSuccessProjection(id.toString(), false), model.getUser());
    }

    @Test
    public void whenModelToString() {

        FetchAuthSuccessByEmailResponse model = model();

        String expected = "FetchAuthSuccessByEmailResponse(user=AuthSuccessProjection(id=7061dca3-b4a9-4c34-bfc5-d9d62f4d8c41, isAdmin=true))";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAuthSuccessByEmailResponse model = model();

        Assertions.assertEquals(1793946915, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchAuthSuccessByEmailResponse model1 = model();
        FetchAuthSuccessByEmailResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchAuthSuccessByEmailResponse model = model();

        Assertions.assertNotEquals(model, new FetchAuthSuccessByEmailResponse(null));
    }

    private FetchAuthSuccessByEmailResponse model() {

        return new FetchAuthSuccessByEmailResponse(new AuthSuccessProjection(UUID.fromString("7061dca3-b4a9-4c34-bfc5-d9d62f4d8c41").toString(), true));
    }
}
