package com.example.demo.web.project.create.form;

import com.google.common.collect.ImmutableMap;
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
    public void whenModelHasAvailableRegionsThenReturnAvailableRegions() {

        ProjectCreateRegionForm model = new ProjectCreateRegionForm();
        model.setAvailableRegions(ImmutableMap.of());

        Assertions.assertEquals(ImmutableMap.of(), model.getAvailableRegions());
    }

    @Test
    public void whenModelToString() {

        ProjectCreateRegionForm model = model();

        String expected = "ProjectCreateRegionForm(selectedRegionId=selectedRegionId, availableRegions={})";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectCreateRegionForm model = model();

        Assertions.assertEquals(-159597177, model.hashCode());
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

        Assertions.assertNotEquals(model, new ProjectCreateRegionForm());
    }

    private ProjectCreateRegionForm model() {

        ProjectCreateRegionForm model = new ProjectCreateRegionForm();
        model.setSelectedRegionId("selectedRegionId");
        model.setAvailableRegions(ImmutableMap.of());

        return model;
    }
}
