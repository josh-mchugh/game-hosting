package com.example.demo.web.project.form;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectCreateFormTest {

    @Test
    public void whenModelHasProjectNameThenReturnProjectName() {

        ProjectCreateForm model = new ProjectCreateForm();
        model.setProjectName("projectName");

        Assertions.assertEquals("projectName", model.getProjectName());
    }

    @Test
    public void whenModelHasSelectedGameIdThenReturnSelectedGameId() {

        ProjectCreateForm model = new ProjectCreateForm();
        model.setSelectedGameId("selectedGameId");

        Assertions.assertEquals("selectedGameId", model.getSelectedGameId());
    }

    @Test
    public void whenModelToString() {

        ProjectCreateForm model = model();

        String expected = "ProjectCreateForm(projectName=projectName, selectedGameId=selectedGameId)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectCreateForm model = model();

        Assertions.assertEquals(1871291245, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectCreateForm model1 = model();
        ProjectCreateForm model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectCreateForm model = model();

        Assertions.assertNotEquals(new ProjectCreateForm(), model);
    }

    private ProjectCreateForm model() {

        ProjectCreateForm model = new ProjectCreateForm();
        model.setProjectName("projectName");
        model.setSelectedGameId("selectedGameId");

        return model;
    }
}
