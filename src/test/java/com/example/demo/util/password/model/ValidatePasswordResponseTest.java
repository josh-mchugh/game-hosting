package com.example.demo.util.password.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidatePasswordResponseTest {

    @Test
    public void whenModelHasValidThenReturnValid() {

        ValidatePasswordResponse model = ValidatePasswordResponse.builder()
                .valid(true)
                .build();

        Assertions.assertTrue(model.isValid());
    }

    @Test
    public void whenModelHasErrorMessageKeyThenReturnMessageKey() {

        ValidatePasswordResponse model = ValidatePasswordResponse.builder()
                .errorMessageKey("errorMessageKey")
                .build();

        Assertions.assertEquals("errorMessageKey", model.getErrorMessageKey());
    }

    @Test
    public void whenModelHasErrorDefaultMessageThenReturnErrorDefaultMessage() {

        ValidatePasswordResponse model = ValidatePasswordResponse.builder()
                .errorDefaultMessage("errorDefaultMessage")
                .build();

        Assertions.assertEquals("errorDefaultMessage", model.getErrorDefaultMessage());
    }

    @Test
    public void whenModelToString() {

        ValidatePasswordResponse model = model();

        String expected = "ValidatePasswordResponse(valid=true, errorMessageKey=errorMessageKey, errorDefaultMessage=errorDefaultMessage)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ValidatePasswordResponse model = model();

        Assertions.assertEquals(1044966952, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ValidatePasswordResponse model1 = model();
        ValidatePasswordResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ValidatePasswordResponse model = model();

        Assertions.assertNotEquals(model, ValidatePasswordResponse.builder().build());
    }

    private ValidatePasswordResponse model() {

        return ValidatePasswordResponse.builder()
                .valid(true)
                .errorMessageKey("errorMessageKey")
                .errorDefaultMessage("errorDefaultMessage")
                .build();
    }
}
