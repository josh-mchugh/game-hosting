package com.example.demo.web.project.create.command.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectAddServerRequestTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectAddServerRequest request = new ProjectAddServerRequest(id, null);

        Assertions.assertEquals(id, request.getId());
    }

    @Test
    public void whenModelHasSelectedFlavorIdThenReturnSelectedFlavorId() {

        ProjectAddServerRequest request = new ProjectAddServerRequest(null, "selectedFlavorId");

        Assertions.assertEquals("selectedFlavorId", request.getSelectedFlavorId());
    }

    @Test
    public void whenModelToString() {

        ProjectAddServerRequest model = model();

        String expected = "ProjectAddServerRequest(id=98fcf7eb-e34a-4727-ba81-87c43b7ac7c1, selectedFlavorId=ovhFlavorid)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectAddServerRequest model = model();

        Assertions.assertEquals(2041816742, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectAddServerRequest model1 = model();
        ProjectAddServerRequest model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectAddServerRequest model = model();

        Assertions.assertNotEquals(model, new ProjectAddServerRequest(null, null));
    }

    private ProjectAddServerRequest model() {

        return new ProjectAddServerRequest(UUID.fromString("98fcf7eb-e34a-4727-ba81-87c43b7ac7c1"), "ovhFlavorid");
    }
}
