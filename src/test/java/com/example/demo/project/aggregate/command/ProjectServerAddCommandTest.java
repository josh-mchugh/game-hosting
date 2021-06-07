package com.example.demo.project.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectServerAddCommandTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectServerAddCommand model = ProjectServerAddCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasOvhFlavorIdThenReturnOvhFlavorId() {

        UUID id = UUID.randomUUID();

        ProjectServerAddCommand model = ProjectServerAddCommand.builder()
                .ovhFlavorId(id)
                .build();

        Assertions.assertEquals(id, model.getOvhFlavorId());
    }

    @Test
    public void whenModelHasOvhImageIdThenReturnOvhImageId() {

        UUID id = UUID.randomUUID();

        ProjectServerAddCommand model = ProjectServerAddCommand.builder()
                .ovhImageId(id)
                .build();

        Assertions.assertEquals(id, model.getOvhImageId());
    }

    @Test
    public void whenModelToString() {

        ProjectServerAddCommand model = model();

        String expected = "ProjectServerAddCommand(id=99800729-51ec-42f8-9ebd-637df5f8b3dd, ovhFlavorId=faf1baa0-1899-4422-90e1-01c855988c97, ovhImageId=faf1baa0-1899-4422-90e1-01c855988c97)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectServerAddCommand model = model();

        Assertions.assertEquals(-930392168, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectServerAddCommand model1 = model();
        ProjectServerAddCommand model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectServerAddCommand model = model();

        Assertions.assertNotEquals(model, ProjectServerAddCommand.builder().build());
    }

    private ProjectServerAddCommand model() {

        return ProjectServerAddCommand.builder()
                .id(UUID.fromString("99800729-51ec-42f8-9ebd-637df5f8b3dd"))
                .ovhFlavorId(UUID.fromString("faf1baa0-1899-4422-90e1-01c855988c97"))
                .ovhImageId(UUID.fromString("faf1baa0-1899-4422-90e1-01c855988c97"))
                .build();
    }
}
