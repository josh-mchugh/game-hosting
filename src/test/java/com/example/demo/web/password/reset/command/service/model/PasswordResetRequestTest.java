package com.example.demo.web.password.reset.command.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordResetRequestTest {

    @Test
    public void whenModelHasTokenThenReturnToken() {

        PasswordResetRequest model = PasswordResetRequest.builder()
                .token("token")
                .build();

        Assertions.assertEquals("token", model.getToken());
    }

    @Test
    public void whenModelHasPasswordThenReturnPassword() {

        PasswordResetRequest model = PasswordResetRequest.builder()
                .password("password")
                .build();

        Assertions.assertEquals("password", model.getPassword());
    }

    @Test
    public void whenModelToString() {

        PasswordResetRequest model = model();

        String expected = "PasswordResetRequest(token=token, password=password)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        PasswordResetRequest model = model();

        Assertions.assertEquals(-851008361, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        PasswordResetRequest model1 = model();
        PasswordResetRequest model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        PasswordResetRequest model = model();

        Assertions.assertNotEquals(model, PasswordResetRequest.builder().build());
    }

    private PasswordResetRequest model() {

        return PasswordResetRequest.builder()
                .token("token")
                .password("password")
                .build();
    }
}
