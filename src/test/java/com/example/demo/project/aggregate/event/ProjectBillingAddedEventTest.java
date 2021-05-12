package com.example.demo.project.aggregate.event;

import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectBillingAddedEventTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectBillingAddedEvent model = ProjectBillingAddedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasStatusThenReturnStatus() {

        ProjectBillingAddedEvent model = ProjectBillingAddedEvent.builder()
                .status(ProjectStatus.BUILD)
                .build();

        Assertions.assertEquals(ProjectStatus.BUILD, model.getStatus());
    }

    @Test
    public void whenModelHasStateThenReturnState() {

        ProjectBillingAddedEvent model = ProjectBillingAddedEvent.builder()
                .state(ProjectState.BUILD_CREATE_INSTANCE_GROUP)
                .build();

        Assertions.assertEquals(ProjectState.BUILD_CREATE_INSTANCE_GROUP, model.getState());
    }

    @Test
    public void whenModelToString() {

        ProjectBillingAddedEvent model = model();

        String expected = "ProjectBillingAddedEvent(id=83770cd0-723d-499f-9d11-ba1952062c50, status=BUILD, state=BUILD_CREATE_INSTANCE_GROUP)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectBillingAddedEvent model = ProjectBillingAddedEvent.builder()
                .id(UUID.fromString("83770cd0-723d-499f-9d11-ba1952062c50"))
                .build();

        Assertions.assertEquals(164468973, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectBillingAddedEvent model1 = model();
        ProjectBillingAddedEvent model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectBillingAddedEvent model = model();

        Assertions.assertNotEquals(model, ProjectBillingAddedEvent.builder().build());
    }

    private ProjectBillingAddedEvent model() {

        return ProjectBillingAddedEvent.builder()
                .id(UUID.fromString("83770cd0-723d-499f-9d11-ba1952062c50"))
                .status(ProjectStatus.BUILD)
                .state(ProjectState.BUILD_CREATE_INSTANCE_GROUP)
                .build();
    }
}
