package com.example.demo.web.registration.command.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegistrationCreateUserRequestTest {

    @Test
    public void whenModelHasEmailThenReturnEmail() {

        RegistrationCreateUserRequest model = RegistrationCreateUserRequest.builder()
                .email("email")
                .build();

        Assertions.assertEquals("email", model.getEmail());
    }

    @Test
    public void whenModelHasPasswordThenReturnPassword() {

        RegistrationCreateUserRequest model = RegistrationCreateUserRequest.builder()
                .password("password")
                .build();

        Assertions.assertEquals("password", model.getPassword());
    }

    @Test
    public void whenModelToString() {

        RegistrationCreateUserRequest model = model();

        String expected = "RegistrationCreateUserRequest(email=email, password=password)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        RegistrationCreateUserRequest model = model();

        Assertions.assertEquals(-1672399576, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        RegistrationCreateUserRequest model1 = model();
        RegistrationCreateUserRequest model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        RegistrationCreateUserRequest model = model();

        Assertions.assertNotEquals(model, RegistrationCreateUserRequest.builder().build());
    }

    private RegistrationCreateUserRequest model() {

        return RegistrationCreateUserRequest.builder()
                .email("email")
                .password("password")
                .build();
    }
}
