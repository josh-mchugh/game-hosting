package com.example.demo.project.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectRegionAddCommandTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectRegionAddCommand model = ProjectRegionAddCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasOvhRegionIdThenReturnOvhRegionId() {

        UUID id = UUID.randomUUID();

        ProjectRegionAddCommand model = ProjectRegionAddCommand.builder()
                .ovhRegionId(id)
                .build();

        Assertions.assertEquals(id, model.getOvhRegionId());
    }

    @Test
    public void whenModelToString() {

        ProjectRegionAddCommand model = model();

        String expected = "ProjectRegionAddCommand(id=99800729-51ec-42f8-9ebd-637df5f8b3dd, ovhRegionId=faf1baa0-1899-4422-90e1-01c855988c97)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectRegionAddCommand model = model();

        Assertions.assertEquals(-1046023551, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectRegionAddCommand model1 = model();
        ProjectRegionAddCommand model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectRegionAddCommand model = model();

        Assertions.assertNotEquals(model, ProjectRegionAddCommand.builder().build());
    }

    private ProjectRegionAddCommand model() {

        return ProjectRegionAddCommand.builder()
                .id(UUID.fromString("99800729-51ec-42f8-9ebd-637df5f8b3dd"))
                .ovhRegionId(UUID.fromString("faf1baa0-1899-4422-90e1-01c855988c97"))
                .build();
    }
}
