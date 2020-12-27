package com.example.demo.user.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchUserDashboardProjectionTest {

    @Test
    public void whenModelHasEmailValidatedThenReturnEmailValidated() {

        FetchUserDashboardProjection model = new FetchUserDashboardProjection(true, false);

        Assertions.assertTrue(model.isEmailValidated());
    }

    @Test
    public void whenModelHasProjectsThenReturnProjects() {

        FetchUserDashboardProjection model = new FetchUserDashboardProjection(false, true);

        Assertions.assertTrue(model.isProjects());
    }

    @Test
    public void whenModelToString() {

        FetchUserDashboardProjection model = model();

        String expected = "FetchUserDashboardProjection(emailValidated=true, projects=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchUserDashboardProjection model = model();

        Assertions.assertEquals(8221, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchUserDashboardProjection model1 = model();
        FetchUserDashboardProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchUserDashboardProjection model = model();

        Assertions.assertNotEquals(model, new FetchUserDashboardProjection(false, false));
    }

    private FetchUserDashboardProjection model() {

        return new FetchUserDashboardProjection(true, true);
    }
}
