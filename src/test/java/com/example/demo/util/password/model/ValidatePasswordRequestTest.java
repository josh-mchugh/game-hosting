package com.example.demo.util.password.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidatePasswordRequestTest {

    @Test
    public void whenModelHasPasswordThenReturnPassword() {

        ValidatePasswordRequest model = new ValidatePasswordRequest("password", null);

        Assertions.assertEquals("password", model.getPassword());
    }

    @Test
    public void whenModelHasConfirmPasswordThenReturnConfirmPassword() {

        ValidatePasswordRequest model = new ValidatePasswordRequest(null, "confirmPassword");

        Assertions.assertEquals("confirmPassword", model.getConfirmPassword());
    }

    @Test
    public void whenModelToString() {

        ValidatePasswordRequest model = model();

        String expected = "ValidatePasswordRequest(password=password, confirmPassword=confirmPassword)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ValidatePasswordRequest model = model();

        Assertions.assertEquals(1004557517, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ValidatePasswordRequest model1 = model();
        ValidatePasswordRequest model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ValidatePasswordRequest model = model();

        Assertions.assertNotEquals(model, new ValidatePasswordRequest(null, null));
    }

    private ValidatePasswordRequest model() {

        return new ValidatePasswordRequest("password", "confirmPassword");
    }
}
