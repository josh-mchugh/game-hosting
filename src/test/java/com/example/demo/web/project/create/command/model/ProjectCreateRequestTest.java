package com.example.demo.web.project.create.command.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectCreateRequestTest {

    @Test
    public void whenModelHasProjectNameThenReturnProjectName() {

        ProjectCreateRequest model = new ProjectCreateRequest("projectName", null);

        Assertions.assertEquals("projectName", model.getProjectName());
    }

    @Test
    public void whenModelHasGameIdThenReturnGameId() {

        ProjectCreateRequest model = new ProjectCreateRequest(null, "gameId");

        Assertions.assertEquals("gameId", model.getGameId());
    }

    @Test
    public void whenModelToString() {

        ProjectCreateRequest model = model();

        String expected = "ProjectCreateRequest(projectName=projectName, gameId=gameId)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectCreateRequest model = model();

        Assertions.assertEquals(-881433358, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectCreateRequest model1 = model();
        ProjectCreateRequest model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectCreateRequest model = model();

        Assertions.assertNotEquals(model, new ProjectCreateRequest(null, null));
    }

    private ProjectCreateRequest model() {

        return new ProjectCreateRequest("projectName", "gameId");
    }
}
