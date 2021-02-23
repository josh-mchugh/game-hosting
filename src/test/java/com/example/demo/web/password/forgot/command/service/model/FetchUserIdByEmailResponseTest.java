package com.example.demo.web.password.forgot.command.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchUserIdByEmailResponseTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FetchUserIdByEmailResponse model = new FetchUserIdByEmailResponse(id) ;

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelToString() {

        FetchUserIdByEmailResponse model = model();

        String expected = "FetchUserIdByEmailResponse(id=b20aaffc-a3d8-437a-8761-e90f9db730a7)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchUserIdByEmailResponse model = model();

        Assertions.assertEquals(184825193, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchUserIdByEmailResponse model1 = model();
        FetchUserIdByEmailResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchUserIdByEmailResponse model = model();

        Assertions.assertNotEquals(model, new FetchUserIdByEmailResponse(UUID.randomUUID()));
    }

    private FetchUserIdByEmailResponse model() {

        return new FetchUserIdByEmailResponse(UUID.fromString("b20aaffc-a3d8-437a-8761-e90f9db730a7"));
    }
}
