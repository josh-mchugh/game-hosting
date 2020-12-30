package com.example.demo.util.password;

import com.example.demo.util.password.model.ValidatePasswordRequest;
import com.example.demo.util.password.model.ValidatePasswordResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordUtilTest {

    @Test
    public void testInvalidValidatePasswords() {

        ValidatePasswordRequest request = new ValidatePasswordRequest("password", "password");
        ValidatePasswordResponse response = PasswordUtil.validatePassword(request);

        Assertions.assertFalse(response.isValid());
        Assertions.assertEquals("Password is invalid", response.getErrorDefaultMessage());
        Assertions.assertEquals("error.password.invalid", response.getErrorMessageKey());
    }

    @Test
    public void testMismatchPasswords() {

        ValidatePasswordRequest request = new ValidatePasswordRequest("password1", "password2");
        ValidatePasswordResponse response = PasswordUtil.validatePassword(request);

        Assertions.assertFalse(response.isValid());
        Assertions.assertEquals("Passwords must match", response.getErrorDefaultMessage());
        Assertions.assertEquals("error.password.mismatch", response.getErrorMessageKey());
    }

    @Test
    public void testEmptyPasswords() {

        ValidatePasswordRequest request = new ValidatePasswordRequest("", "");
        ValidatePasswordResponse response = PasswordUtil.validatePassword(request);

        Assertions.assertFalse(response.isValid());
        Assertions.assertEquals("Password is invalid", response.getErrorDefaultMessage());
        Assertions.assertEquals("error.password.invalid", response.getErrorMessageKey());
    }

    @Test
    public void testValidPasswords() {

        ValidatePasswordRequest request = new ValidatePasswordRequest("Password1!", "Password1!");
        ValidatePasswordResponse response = PasswordUtil.validatePassword(request);

        Assertions.assertTrue(response.isValid());
    }
}
