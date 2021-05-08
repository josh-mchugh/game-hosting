package com.example.demo.web.project.create.command.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectAddRegionRequestTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectAddRegionRequest request = new ProjectAddRegionRequest(id, null);

        Assertions.assertEquals(id, request.getId());
    }

    @Test
    public void whenModelHasSelectedRegionIdThenReturnSelectedRegionId() {

        ProjectAddRegionRequest request = new ProjectAddRegionRequest(null, "selectedRegionId");

        Assertions.assertEquals("selectedRegionId", request.getSelectedRegionId());
    }

    @Test
    public void whenModelToString() {

        ProjectAddRegionRequest model = model();

        String expected = "ProjectAddRegionRequest(id=98fcf7eb-e34a-4727-ba81-87c43b7ac7c1, selectedRegionId=selectedRegionId)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectAddRegionRequest model = model();

        Assertions.assertEquals(1930320598, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectAddRegionRequest model1 = model();
        ProjectAddRegionRequest model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectAddRegionRequest model = model();

        Assertions.assertNotEquals(model, new ProjectAddRegionRequest(null, null));
    }

    private ProjectAddRegionRequest model() {

        return new ProjectAddRegionRequest(UUID.fromString("98fcf7eb-e34a-4727-ba81-87c43b7ac7c1"), "selectedRegionId");
    }
}
