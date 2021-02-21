package com.example.demo.web.password.reset.form;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResetPasswordFormTest {

    @Test
    public void whenFormHasPasswordThenReturnPassword() {

        ResetPasswordForm form = new ResetPasswordForm();
        form.setPassword("password");

        Assertions.assertEquals("password", form.getPassword());
    }

    @Test
    public void whenFormHasConfirmPasswordThenReturnConfirmPassword() {

        ResetPasswordForm form = new ResetPasswordForm();
        form.setConfirmPassword("confirmPassword");

        Assertions.assertEquals("confirmPassword", form.getConfirmPassword());
    }

    @Test
    public void whenFormToString() {

        ResetPasswordForm form = form();

        String expected = "ResetPasswordForm(password=password, confirmPassword=confirmPassword)";

        Assertions.assertEquals(expected, form.toString());
    }

    @Test
    public void whenFormHashCode() {

        ResetPasswordForm form = form();

        Assertions.assertEquals(1004557517, form.hashCode());
    }

    @Test
    public void whenFormEquals() {

        ResetPasswordForm form1 = form();
        ResetPasswordForm form2 = form();

        Assertions.assertEquals(form1, form2);
    }

    @Test
    public void whenFormNotEquals() {

        ResetPasswordForm form = form();

        Assertions.assertNotEquals(form, new ResetPasswordForm());
    }

    private ResetPasswordForm form() {

        ResetPasswordForm form = new ResetPasswordForm();
        form.setPassword("password");
        form.setConfirmPassword("confirmPassword");

        return form;
    }
}
