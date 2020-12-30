package com.example.demo.web.project.command.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectInstanceStartRequestTest {

    @Test
    public void whenModelHasProjectIdThenReturnProjectId() {

        ProjectInstanceStartRequest model = ProjectInstanceStartRequest.builder()
                .projectId("projectId")
                .build();

        Assertions.assertEquals("projectId", model.getProjectId());
    }

    @Test
    public void whenModelHasInstanceIdThenReturnInstanceId() {

        ProjectInstanceStartRequest model = ProjectInstanceStartRequest.builder()
                .instanceId("instanceId")
                .build();

        Assertions.assertEquals("instanceId", model.getInstanceId());
    }

    @Test
    public void whenModelToString() {

        ProjectInstanceStartRequest request = model();

        String expected = "ProjectInstanceStartRequest(projectId=projectId, instanceId=instanceId)";

        Assertions.assertEquals(expected, request.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectInstanceStartRequest model = model();

        Assertions.assertEquals(-353459003, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectInstanceStartRequest model1 = model();
        ProjectInstanceStartRequest model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectInstanceStartRequest model = model();

        Assertions.assertNotEquals(model, ProjectInstanceStartRequest.builder().build());
    }

    private ProjectInstanceStartRequest model() {

        return ProjectInstanceStartRequest.builder()
                .projectId("projectId")
                .instanceId("instanceId")
                .build();
    }
}
