package com.example.demo.web.project.create.command.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectAddBillingRequestTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectAddBillingRequest model = new ProjectAddBillingRequest(id);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelToString() {

        ProjectAddBillingRequest model = model();

        String expected = "ProjectAddBillingRequest(id=0e107c2c-ba77-4d57-a78c-cb5b18bde58b)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectAddBillingRequest model = model();

        Assertions.assertEquals(190193638, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectAddBillingRequest model1 = model();
        ProjectAddBillingRequest model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectAddBillingRequest model = model();

        Assertions.assertNotEquals(model, new ProjectAddBillingRequest(null));
    }

    private ProjectAddBillingRequest model() {

        return new ProjectAddBillingRequest(UUID.fromString("0e107c2c-ba77-4d57-a78c-cb5b18bde58b"));
    }
}
