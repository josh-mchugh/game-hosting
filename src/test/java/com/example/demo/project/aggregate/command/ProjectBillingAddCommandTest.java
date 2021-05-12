package com.example.demo.project.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectBillingAddCommandTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectBillingAddCommand model = new ProjectBillingAddCommand(id);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelToString() {

        ProjectBillingAddCommand model = model();

        String expected = "ProjectBillingAddCommand(id=99800729-51ec-42f8-9ebd-637df5f8b3dd)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectBillingAddCommand model = model();

        Assertions.assertEquals(-1557555796, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectBillingAddCommand model1 = model();
        ProjectBillingAddCommand model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectBillingAddCommand model = model();

        Assertions.assertNotEquals(model, new ProjectBillingAddCommand(null));
    }

    private ProjectBillingAddCommand model() {

        return new ProjectBillingAddCommand(UUID.fromString("99800729-51ec-42f8-9ebd-637df5f8b3dd"));
    }
}
