package com.example.demo.project.entity.model;

import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        Project project = Project.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, project.getId());
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

        Project project = project();

        String expected = "Project(id=c856f635-ef7f-4dfd-b2de-f600a94f11f1, name=name, status=ACTIVE, state=ACTIVE)";

        Assertions.assertEquals(expected, project.toString());
    }

    @Test
    public void whenModelHashCode() {

        Project project = Project.builder()
                .id(UUID.fromString("c856f635-ef7f-4dfd-b2de-f600a94f11f1"))
                .name("name")
                .build();

        Assertions.assertEquals(836825219, project.hashCode());
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
                .id(UUID.fromString("c856f635-ef7f-4dfd-b2de-f600a94f11f1"))
                .name("name")
                .state(ProjectState.ACTIVE)
                .status(ProjectStatus.ACTIVE)
                .build();
    }
}
