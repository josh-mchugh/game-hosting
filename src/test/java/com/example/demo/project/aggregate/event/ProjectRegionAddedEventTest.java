package com.example.demo.project.aggregate.event;

import com.example.demo.project.entity.ProjectState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectRegionAddedEventTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectRegionAddedEvent model = ProjectRegionAddedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasOvhRegionIdThenReturnOvhRegionId() {

        UUID id = UUID.randomUUID();

        ProjectRegionAddedEvent model = ProjectRegionAddedEvent.builder()
                .ovhRegionId(id)
                .build();

        Assertions.assertEquals(id, model.getOvhRegionId());
    }

    @Test
    public void whenModelHasStateThenReturnState() {

        ProjectRegionAddedEvent model = ProjectRegionAddedEvent.builder()
                .state(ProjectState.CONFIG_SERVER)
                .build();

        Assertions.assertEquals(ProjectState.CONFIG_SERVER, model.getState());
    }

    @Test
    public void whenModelToString() {

        ProjectRegionAddedEvent model = model();

        String expected = "ProjectRegionAddedEvent(id=83770cd0-723d-499f-9d11-ba1952062c50, ovhRegionId=571a69ce-f2d5-465c-8685-f7417c96145d, state=CONFIG_SERVER)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectRegionAddedEvent model = ProjectRegionAddedEvent.builder()
                .id(UUID.fromString("83770cd0-723d-499f-9d11-ba1952062c50"))
                .ovhRegionId(UUID.fromString("571a69ce-f2d5-465c-8685-f7417c96145d"))
                .build();

        Assertions.assertEquals(565228478, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectRegionAddedEvent model1 = model();
        ProjectRegionAddedEvent model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectRegionAddedEvent model = model();

        Assertions.assertNotEquals(model, ProjectRegionAddedEvent.builder().build());
    }

    private ProjectRegionAddedEvent model() {

        return ProjectRegionAddedEvent.builder()
                .id(UUID.fromString("83770cd0-723d-499f-9d11-ba1952062c50"))
                .ovhRegionId(UUID.fromString("571a69ce-f2d5-465c-8685-f7417c96145d"))
                .state(ProjectState.CONFIG_SERVER)
                .build();
    }
}
