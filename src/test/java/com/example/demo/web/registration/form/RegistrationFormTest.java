package com.example.demo.web.registration.form;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegistrationFormTest {

    @Test
    public void whenFormHasEmailThenReturnEmail() {

        RegistrationForm form = new RegistrationForm();
        form.setEmail("email");

        Assertions.assertEquals("email", form.getEmail());
    }

    @Test
    public void whenFormHasPasswordThenReturnPassword() {

        RegistrationForm form = new RegistrationForm();
        form.setPassword("password");

        Assertions.assertEquals("password", form.getPassword());
    }

    @Test
    public void whenFormHasConfirmPasswordThenReturnConfirmPassword() {

        RegistrationForm form = new RegistrationForm();
        form.setConfirmPassword("confirmPassword");

        Assertions.assertEquals("confirmPassword", form.getConfirmPassword());
    }

    @Test
    public void whenFormToString() {

        RegistrationForm form = form();

        String expected = "RegistrationForm(email=email, password=password, confirmPassword=confirmPassword)";

        Assertions.assertEquals(expected, form.toString());
    }

    @Test
    public void whenFormHashCode() {

        RegistrationForm form = form();

        Assertions.assertEquals(-1965455949, form.hashCode());
    }

    @Test
    public void whenFormEquals() {

        RegistrationForm form1 = form();
        RegistrationForm form2 = form();

        Assertions.assertEquals(form1, form2);
    }

    @Test
    public void whenFormNotEquals() {

        RegistrationForm form  = form();

        Assertions.assertNotEquals(form, new RegistrationForm());
    }

    private RegistrationForm form() {

        RegistrationForm form = new RegistrationForm();
        form.setEmail("email");
        form.setPassword("password");
        form.setConfirmPassword("confirmPassword");

        return form;
    }
}
