package com.example.demo.project.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectFlavorAddCommandTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectFlavorAddCommand model = ProjectFlavorAddCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasOvhFlavorIdThenReturnOvhFlavorId() {

        UUID id = UUID.randomUUID();

        ProjectFlavorAddCommand model = ProjectFlavorAddCommand.builder()
                .ovhFlavorId(id)
                .build();

        Assertions.assertEquals(id, model.getOvhFlavorId());
    }

    @Test
    public void whenModelToString() {

        ProjectFlavorAddCommand model = model();

        String expected = "ProjectFlavorAddCommand(id=99800729-51ec-42f8-9ebd-637df5f8b3dd, ovhFlavorId=faf1baa0-1899-4422-90e1-01c855988c97)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectFlavorAddCommand model = model();

        Assertions.assertEquals(-1046023551, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectFlavorAddCommand model1 = model();
        ProjectFlavorAddCommand model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectFlavorAddCommand model = model();

        Assertions.assertNotEquals(model, ProjectFlavorAddCommand.builder().build());
    }

    private ProjectFlavorAddCommand model() {

        return ProjectFlavorAddCommand.builder()
                .id(UUID.fromString("99800729-51ec-42f8-9ebd-637df5f8b3dd"))
                .ovhFlavorId(UUID.fromString("faf1baa0-1899-4422-90e1-01c855988c97"))
                .build();
    }
}
