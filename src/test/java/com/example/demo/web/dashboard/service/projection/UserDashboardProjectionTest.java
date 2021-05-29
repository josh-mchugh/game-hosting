package com.example.demo.web.dashboard.service.projection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserDashboardProjectionTest {

    @Test
    public void whenModelHasEmailValidatedThenReturnEmailValidated() {

        UserDashboardProjection model = new UserDashboardProjection(true, false);

        Assertions.assertTrue(model.isEmailValidated());
    }

    @Test
    public void whenModelHasProjectsThenReturnProjects() {

        UserDashboardProjection model = new UserDashboardProjection(false, true);

        Assertions.assertTrue(model.isProjects());
    }

    @Test
    public void whenModelToString() {

        UserDashboardProjection model = model();

        String expected = "UserDashboardProjection(emailValidated=true, projects=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        UserDashboardProjection model = model();

        Assertions.assertEquals(8221, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        UserDashboardProjection model1 = model();
        UserDashboardProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        UserDashboardProjection model = model();

        Assertions.assertNotEquals(model, new UserDashboardProjection(false, false));
    }

    private UserDashboardProjection model() {

        return new UserDashboardProjection(true, true);
    }
}
