package com.example.demo.project.entity.model;

import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        Project project = Project.builder()
                .id("id")
                .build();

        Assertions.assertEquals("id", project.getId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        Project project = Project.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", project.getName());
    }

    @Test
    public void whenModelHasStatusThenReturnStatus() {

        Project project = Project.builder()
                .status(ProjectStatus.ACTIVE)
                .build();

        Assertions.assertEquals(ProjectStatus.ACTIVE, project.getStatus());
    }

    @Test
    public void whenModelHasStateThenReturnState() {

        Project project = Project.builder()
                .state(ProjectState.ACTIVE)
                .build();

        Assertions.assertEquals(ProjectState.ACTIVE, project.getState());
    }

    @Test
    public void whenModelToString() {

        Project project = Project.builder()
                .id("id")
                .name("name")
                .state(ProjectState.ACTIVE)
                .status(ProjectStatus.ACTIVE)
                .build();

        String expected = "Project(id=id, name=name, status=ACTIVE, state=ACTIVE)";

        Assertions.assertEquals(expected, project.toString());
    }

    @Test
    public void whenModelHashCode() {

        Project project = project();

        Assertions.assertEquals(-439861335, project.hashCode());
    }

    @Test
    public void whenModelEquals() {

        Project project1 = project();
        Project project2 = project();

        Assertions.assertEquals(project1, project2);
    }

    @Test
    public void whenModelNotEquals() {

        Project project = project();

        Assertions.assertNotEquals(project, Project.builder().build());
    }

    private Project project() {

        return Project.builder()
                .id("id")
                .name("name")
                .build();
    }
}
