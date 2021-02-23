package com.example.demo.web.password.forgot.form;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ForgotPasswordFormTest {

    @Test
    public void whenFormHasEmailThenReturnEmail() {

        ForgotPasswordForm form = new ForgotPasswordForm();
        form.setEmail("email");

        Assertions.assertEquals("email", form.getEmail());
    }

    @Test
    public void whenFormToString() {

        ForgotPasswordForm form = form();

        String expected = "ForgotPasswordForm(email=email)";

        Assertions.assertEquals(expected, form.toString());
    }

    @Test
    public void whenFormHashCode() {

        ForgotPasswordForm form = form();

        Assertions.assertEquals(96619479, form.hashCode());
    }

    @Test
    public void whenFormEquals() {

        ForgotPasswordForm form1 = form();
        ForgotPasswordForm form2 = form();

        Assertions.assertEquals(form1, form2);
    }

    @Test
    public void whenFormNotEquals() {

        ForgotPasswordForm form = form();

        Assertions.assertNotEquals(form, new ForgotPasswordForm());
    }

    private ForgotPasswordForm form() {

        ForgotPasswordForm form = new ForgotPasswordForm();
        form.setEmail("email");

        return form;
    }
}
