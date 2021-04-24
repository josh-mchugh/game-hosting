package com.example.demo.web.project.create.form;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectCreateRegionFormTest {

    @Test
    public void whenModelHasSelectedRegionIdThenReturnSelectedRegionId() {

        ProjectCreateRegionForm model = new ProjectCreateRegionForm();
        model.setSelectedRegionId("selectedRegionId");

        Assertions.assertEquals("selectedRegionId", model.getSelectedRegionId());
    }

    @Test
    public void whenModelToString() {

        ProjectCreateRegionForm model = model();

        String expected = "ProjectCreateRegionForm(selectedRegionId=selectedRegionId)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectCreateRegionForm model = model();

        Assertions.assertEquals(-1021849819, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectCreateRegionForm model1 = model();
        ProjectCreateRegionForm model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectCreateRegionForm model = model();

        Assertions.assertNotEquals(new ProjectCreateForm(), model);
    }

    private ProjectCreateRegionForm model() {

        ProjectCreateRegionForm model = new ProjectCreateRegionForm();
        model.setSelectedRegionId("selectedRegionId");

        return model;
    }
}
