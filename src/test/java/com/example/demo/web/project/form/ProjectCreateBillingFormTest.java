package com.example.demo.web.project.form;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectCreateBillingFormTest {

    @Test
    public void whenModelHasCardNumberThenReturnCardNumber() {

        ProjectCreateBillingForm model = new ProjectCreateBillingForm();
        model.setCardNumber("cardNumber");

        Assertions.assertEquals("cardNumber", model.getCardNumber());
    }

    @Test
    public void whenModelToString() {

        ProjectCreateBillingForm model = model();

        String expected = "ProjectCreateBillingForm(cardNumber=cardNumber)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectCreateBillingForm model = model();

        Assertions.assertEquals(508016308, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectCreateBillingForm model1 = model();
        ProjectCreateBillingForm model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectCreateBillingForm model = model();

        Assertions.assertNotEquals(new ProjectCreateBillingForm(), model);
    }

    private ProjectCreateBillingForm model() {

        ProjectCreateBillingForm model = new ProjectCreateBillingForm();
        model.setCardNumber("cardNumber");

        return model;
    }
}
