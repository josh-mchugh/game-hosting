package com.example.demo.project.aggregate.event;

import com.example.demo.project.entity.ProjectState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectServerAddedEventTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectServerAddedEvent model = ProjectServerAddedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasOvhFlavorIdThenReturnOvhFlavorId() {

        UUID id = UUID.randomUUID();

        ProjectServerAddedEvent model = ProjectServerAddedEvent.builder()
                .ovhFlavorId(id)
                .build();

        Assertions.assertEquals(id, model.getOvhFlavorId());
    }

    @Test
    public void whenModelHasOvhImageIdThenReturnOvhImageId() {

        UUID id = UUID.randomUUID();

        ProjectServerAddedEvent model = ProjectServerAddedEvent.builder()
                .ovhImageId(id)
                .build();

        Assertions.assertEquals(id, model.getOvhImageId());
    }

    @Test
    public void whenModelHasStateThenReturnState() {

        ProjectServerAddedEvent model = ProjectServerAddedEvent.builder()
                .state(ProjectState.CONFIG_BILLING)
                .build();

        Assertions.assertEquals(ProjectState.CONFIG_BILLING, model.getState());
    }

    @Test
    public void whenModelToString() {

        ProjectServerAddedEvent model = model();

        String expected = "ProjectServerAddedEvent(id=83770cd0-723d-499f-9d11-ba1952062c50, ovhFlavorId=571a69ce-f2d5-465c-8685-f7417c96145d, ovhImageId=571a69ce-f2d5-465c-8685-f7417c96145d, state=CONFIG_BILLING)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectServerAddedEvent model = ProjectServerAddedEvent.builder()
                .id(UUID.fromString("83770cd0-723d-499f-9d11-ba1952062c50"))
                .ovhFlavorId(UUID.fromString("571a69ce-f2d5-465c-8685-f7417c96145d"))
                .build();

        Assertions.assertEquals(-1011258123, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectServerAddedEvent model1 = model();
        ProjectServerAddedEvent model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectServerAddedEvent model = model();

        Assertions.assertNotEquals(model, ProjectServerAddedEvent.builder().build());
    }

    private ProjectServerAddedEvent model() {

        return ProjectServerAddedEvent.builder()
                .id(UUID.fromString("83770cd0-723d-499f-9d11-ba1952062c50"))
                .ovhFlavorId(UUID.fromString("571a69ce-f2d5-465c-8685-f7417c96145d"))
                .ovhImageId(UUID.fromString("571a69ce-f2d5-465c-8685-f7417c96145d"))
                .state(ProjectState.CONFIG_BILLING)
                .build();
    }
}
