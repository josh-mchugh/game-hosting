package com.example.demo.web.project.util.model;

import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectStateValidationRequestTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectStateValidationRequest model = ProjectStateValidationRequest.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasCurrentStatusThenReturnCurrentStatus() {

        ProjectStateValidationRequest model = ProjectStateValidationRequest.builder()
                .currentStatus(ProjectStatus.CONFIG)
                .build();

        Assertions.assertEquals(ProjectStatus.CONFIG, model.getCurrentStatus());
    }

    @Test
    public void whenModelHasCurrentStateThenReturnCurrentState() {

        ProjectStateValidationRequest model = ProjectStateValidationRequest.builder()
                .currentState(ProjectState.CONFIG_REGION)
                .build();

        Assertions.assertEquals(ProjectState.CONFIG_REGION, model.getCurrentState());
    }

    @Test
    public void whenModelHasExpectedStatusThenReturnExpectedStatus() {

        ProjectStateValidationRequest model = ProjectStateValidationRequest.builder()
                .expectedStatus(ProjectStatus.CONFIG)
                .build();

        Assertions.assertEquals(ProjectStatus.CONFIG, model.getExpectedStatus());
    }

    @Test
    public void whenModelHasExpectedStateThenReturnExpectedState() {

        ProjectStateValidationRequest model = ProjectStateValidationRequest.builder()
                .expectedState(ProjectState.CONFIG_REGION)
                .build();

        Assertions.assertEquals(ProjectState.CONFIG_REGION, model.getExpectedState());
    }

    @Test
    public void whenModelToString() {

        ProjectStateValidationRequest model = model();

        String expected = "ProjectStateValidationRequest(id=fea517ad-7ed1-4df5-bbd6-7ad826b6a1ae, currentStatus=CONFIG, currentState=CONFIG_REGION, expectedStatus=CONFIG, expectedState=CONFIG_REGION)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectStateValidationRequest model = ProjectStateValidationRequest.builder()
                .id(UUID.fromString("fea517ad-7ed1-4df5-bbd6-7ad826b6a1ae"))
                .build();

        Assertions.assertEquals(835788897, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectStateValidationRequest model1 = model();
        ProjectStateValidationRequest model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectStateValidationRequest model = model();

        Assertions.assertNotEquals(model, ProjectStateValidationRequest.builder().build());
    }

    private ProjectStateValidationRequest model() {

        return ProjectStateValidationRequest.builder()
                .id(UUID.fromString("fea517ad-7ed1-4df5-bbd6-7ad826b6a1ae"))
                .currentStatus(ProjectStatus.CONFIG)
                .currentState(ProjectState.CONFIG_REGION)
                .expectedStatus(ProjectStatus.CONFIG)
                .expectedState(ProjectState.CONFIG_REGION)
                .build();
    }
}
