package com.example.demo.web.dashboard.projection.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchDashboardDetailsQueryTest {

    @Test
    public void whenModelHasEmailThenReturnEmail() {

        FetchDashboardDetailsQuery model = new FetchDashboardDetailsQuery("email");

        Assertions.assertEquals("email", model.getEmail());
    }

    @Test
    public void whenModelToString() {

        FetchDashboardDetailsQuery model = model();

        String expected = "FetchDashboardDetailsQuery(email=email)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchDashboardDetailsQuery model = model();

        Assertions.assertEquals(96619479, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchDashboardDetailsQuery model1 = model();
        FetchDashboardDetailsQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchDashboardDetailsQuery model = model();

        Assertions.assertNotEquals(model, new FetchDashboardDetailsQuery(null));
    }

    private FetchDashboardDetailsQuery model() {

        return new FetchDashboardDetailsQuery("email");
    }
}
