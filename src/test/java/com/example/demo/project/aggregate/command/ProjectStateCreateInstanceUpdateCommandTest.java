package com.example.demo.project.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectStateCreateInstanceUpdateCommandTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectStateCreateInstanceUpdateCommand model = new ProjectStateCreateInstanceUpdateCommand(id);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelToString() {

        ProjectStateCreateInstanceUpdateCommand model = model();

        String expected = "ProjectStateCreateInstanceUpdateCommand(id=99800729-51ec-42f8-9ebd-637df5f8b3dd)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectStateCreateInstanceUpdateCommand model = model();

        Assertions.assertEquals(-1557555796, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectStateCreateInstanceUpdateCommand model1 = model();
        ProjectStateCreateInstanceUpdateCommand model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectStateCreateInstanceUpdateCommand model = model();

        Assertions.assertNotEquals(model, new ProjectStateCreateInstanceUpdateCommand(null));
    }

    private ProjectStateCreateInstanceUpdateCommand model() {

        return new ProjectStateCreateInstanceUpdateCommand(UUID.fromString("99800729-51ec-42f8-9ebd-637df5f8b3dd"));
    }
}
