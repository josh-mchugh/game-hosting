package com.example.demo.ovh.feign;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlanCodeApiTest {

    @Test
    public void whenModelHasHourlyThenReturnHourly() {

        PlanCodeApi model = new PlanCodeApi();
        model.setHourly("hourly");

        Assertions.assertEquals("hourly", model.getHourly());
    }

    @Test
    public void whenModelHasMonthlyThenReturnMonthly() {

        PlanCodeApi model = new PlanCodeApi();
        model.setMonthly("monthly");

        Assertions.assertEquals("monthly", model.getMonthly());
    }

    @Test
    public void whenModelToString() {

        PlanCodeApi model = model();

        String expected = "PlanCodeApi(hourly=hourly, monthly=monthly)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        PlanCodeApi model = model();

        Assertions.assertEquals(-1518029391, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        PlanCodeApi model1 = model();
        PlanCodeApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        PlanCodeApi model = model();

        Assertions.assertNotEquals(model, new PlanCodeApi());
    }

    private PlanCodeApi model() {

        PlanCodeApi model = new PlanCodeApi();
        model.setHourly("hourly");
        model.setMonthly("monthly");

        return model;
    }
}
