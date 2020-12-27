package com.example.demo.web.dashboard.command.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DashboardProjectCreateResponseTest {

    @Test
    public void whenModelHasProjectIdThenReturnProjectId() {

        DashboardProjectCreateResponse model = new DashboardProjectCreateResponse("projectId");

        Assertions.assertEquals("projectId", model.getProjectId());
    }

    @Test
    public void whenModelToString() {

        DashboardProjectCreateResponse model = model();

        String expected = "DashboardProjectCreateResponse(projectId=projectId)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        DashboardProjectCreateResponse model = model();

        Assertions.assertEquals(-894832049, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        DashboardProjectCreateResponse model1 = model();
        DashboardProjectCreateResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        DashboardProjectCreateResponse model = model();

        Assertions.assertNotEquals(model, new DashboardProjectCreateResponse("diffProjectId"));
    }

    private DashboardProjectCreateResponse model() {

        return new DashboardProjectCreateResponse("projectId");
    }
}
