package com.example.demo.web.project.dashboard.command.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectInstanceStopRequestTest {

    @Test
    public void whenModelHasProjectIdThenReturnProjectId() {

        ProjectInstanceStopRequest model = ProjectInstanceStopRequest.builder()
                .projectId("projectId")
                .build();

        Assertions.assertEquals("projectId", model.getProjectId());
    }

    @Test
    public void whenModelHasInstanceIdThenReturnInstanceId() {

        ProjectInstanceStopRequest model = ProjectInstanceStopRequest.builder()
                .instanceId("instanceId")
                .build();

        Assertions.assertEquals("instanceId", model.getInstanceId());
    }

    @Test
    public void whenModelToString() {

        ProjectInstanceStopRequest request = model();

        String expected = "ProjectInstanceStopRequest(projectId=projectId, instanceId=instanceId)";

        Assertions.assertEquals(expected, request.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectInstanceStopRequest model = model();

        Assertions.assertEquals(-353459003, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectInstanceStopRequest model1 = model();
        ProjectInstanceStopRequest model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectInstanceStopRequest model = model();

        Assertions.assertNotEquals(model, ProjectInstanceStopRequest.builder().build());
    }

    private ProjectInstanceStopRequest model() {

        return ProjectInstanceStopRequest.builder()
                .projectId("projectId")
                .instanceId("instanceId")
                .build();
    }
}
