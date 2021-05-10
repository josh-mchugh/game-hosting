package com.example.demo.project.aggregate.event;

import com.example.demo.project.entity.ProjectState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectFlavorAddedEventTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectFlavorAddedEvent model = ProjectFlavorAddedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasOvhFlavorIdThenReturnOvhFlavorId() {

        UUID id = UUID.randomUUID();

        ProjectFlavorAddedEvent model = ProjectFlavorAddedEvent.builder()
                .ovhFlavorId(id)
                .build();

        Assertions.assertEquals(id, model.getOvhFlavorId());
    }

    @Test
    public void whenModelHasStateThenReturnState() {

        ProjectFlavorAddedEvent model = ProjectFlavorAddedEvent.builder()
                .state(ProjectState.CONFIG_BILLING)
                .build();

        Assertions.assertEquals(ProjectState.CONFIG_BILLING, model.getState());
    }

    @Test
    public void whenModelToString() {

        ProjectFlavorAddedEvent model = model();

        String expected = "ProjectFlavorAddedEvent(id=83770cd0-723d-499f-9d11-ba1952062c50, ovhFlavorId=571a69ce-f2d5-465c-8685-f7417c96145d, state=CONFIG_BILLING)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectFlavorAddedEvent model = ProjectFlavorAddedEvent.builder()
                .id(UUID.fromString("83770cd0-723d-499f-9d11-ba1952062c50"))
                .ovhFlavorId(UUID.fromString("571a69ce-f2d5-465c-8685-f7417c96145d"))
                .build();

        Assertions.assertEquals(565228478, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectFlavorAddedEvent model1 = model();
        ProjectFlavorAddedEvent model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectFlavorAddedEvent model = model();

        Assertions.assertNotEquals(model, ProjectFlavorAddedEvent.builder().build());
    }

    private ProjectFlavorAddedEvent model() {

        return ProjectFlavorAddedEvent.builder()
                .id(UUID.fromString("83770cd0-723d-499f-9d11-ba1952062c50"))
                .ovhFlavorId(UUID.fromString("571a69ce-f2d5-465c-8685-f7417c96145d"))
                .state(ProjectState.CONFIG_BILLING)
                .build();
    }
}
