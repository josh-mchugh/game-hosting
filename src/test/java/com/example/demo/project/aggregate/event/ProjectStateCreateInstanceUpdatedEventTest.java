package com.example.demo.project.aggregate.event;

import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectStateCreateInstanceUpdatedEventTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectStateCreateInstanceUpdatedEvent model = ProjectStateCreateInstanceUpdatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasStatusThenReturnStatus() {

        ProjectStateCreateInstanceUpdatedEvent model = ProjectStateCreateInstanceUpdatedEvent.builder()
                .status(ProjectStatus.BUILD)
                .build();

        Assertions.assertEquals(ProjectStatus.BUILD, model.getStatus());
    }

    @Test
    public void whenModelHasStateThenReturnState() {

        ProjectStateCreateInstanceUpdatedEvent model = ProjectStateCreateInstanceUpdatedEvent.builder()
                .state(ProjectState.BUILD_CREATE_INSTANCE)
                .build();

        Assertions.assertEquals(ProjectState.BUILD_CREATE_INSTANCE, model.getState());
    }

    @Test
    public void whenModelToString() {

        ProjectStateCreateInstanceUpdatedEvent model = model();

        String expected = "ProjectStateCreateInstanceUpdatedEvent(id=83770cd0-723d-499f-9d11-ba1952062c50, status=BUILD, state=BUILD_CREATE_INSTANCE)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectStateCreateInstanceUpdatedEvent model = ProjectStateCreateInstanceUpdatedEvent.builder()
                .id(UUID.fromString("83770cd0-723d-499f-9d11-ba1952062c50"))
                .build();

        Assertions.assertEquals(164468973, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectStateCreateInstanceUpdatedEvent model1 = model();
        ProjectStateCreateInstanceUpdatedEvent model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectStateCreateInstanceUpdatedEvent model = model();

        Assertions.assertNotEquals(model, ProjectStateCreateInstanceUpdatedEvent.builder().build());
    }

    private ProjectStateCreateInstanceUpdatedEvent model() {

        return ProjectStateCreateInstanceUpdatedEvent.builder()
                .id(UUID.fromString("83770cd0-723d-499f-9d11-ba1952062c50"))
                .status(ProjectStatus.BUILD)
                .state(ProjectState.BUILD_CREATE_INSTANCE)
                .build();
    }
}
