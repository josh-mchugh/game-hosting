package com.example.demo.web.project.create.command.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectAddFlavorRequestTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectAddFlavorRequest request = new ProjectAddFlavorRequest(id, null);

        Assertions.assertEquals(id, request.getId());
    }

    @Test
    public void whenModelHasSelectedFlavorIdThenReturnSelectedFlavorId() {

        ProjectAddFlavorRequest request = new ProjectAddFlavorRequest(null, "selectedFlavorId");

        Assertions.assertEquals("selectedFlavorId", request.getSelectedFlavorId());
    }

    @Test
    public void whenModelToString() {

        ProjectAddFlavorRequest model = model();

        String expected = "ProjectAddFlavorRequest(id=98fcf7eb-e34a-4727-ba81-87c43b7ac7c1, selectedFlavorId=ovhFlavorid)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectAddFlavorRequest model = model();

        Assertions.assertEquals(2041816742, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectAddFlavorRequest model1 = model();
        ProjectAddFlavorRequest model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectAddFlavorRequest model = model();

        Assertions.assertNotEquals(model, new ProjectAddFlavorRequest(null, null));
    }

    private ProjectAddFlavorRequest model() {

        return new ProjectAddFlavorRequest(UUID.fromString("98fcf7eb-e34a-4727-ba81-87c43b7ac7c1"), "ovhFlavorid");
    }
}
