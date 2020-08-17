package com.example.demo.project.mapper;

import com.example.demo.project.entity.ProjectEntity;
import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import com.example.demo.project.model.Project;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectMapperTest {

    @Test
    public void whenEntityIsNullThenReturnNull() {

        Assertions.assertNull(ProjectMapper.map(null));
    }

    @Test
    public void whenEntityIsNotNullThenReturnProject() {

        Assertions.assertNotNull(ProjectMapper.map(new ProjectEntity()));
    }

    @Test
    public void whenEntityHasIdThenReturnId() {

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("id");

        Project project = ProjectMapper.map(projectEntity);

        Assertions.assertEquals("id", project.getId());
    }

    @Test
    public void whenEntityHasNullIdThenReturnNull() {

        Project project = ProjectMapper.map(new ProjectEntity());

        Assertions.assertNull(project.getId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName("name");

        Project project = ProjectMapper.map(projectEntity);

        Assertions.assertEquals("name", project.getName());
    }

    @Test
    public void whenEntityHasNullNameThenReturnNull() {

        Project project = ProjectMapper.map(new ProjectEntity());

        Assertions.assertNull(project.getName());
    }

    @Test
    public void whenEntityHasStatusThenReturnStatus() {

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setStatus(ProjectStatus.ACTIVE);

        Project project = ProjectMapper.map(projectEntity);

        Assertions.assertEquals(ProjectStatus.ACTIVE, project.getStatus());
    }

    @Test
    public void whenEntityHasNullStatusThenReturnNull() {

        Project project = ProjectMapper.map(new ProjectEntity());

        Assertions.assertNull(project.getStatus());
    }

    @Test
    public void whenEntityHasStateThenReturnState() {

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setState(ProjectState.BUILD);

        Project project = ProjectMapper.map(projectEntity);

        Assertions.assertEquals(ProjectState.BUILD, project.getState());
    }

    @Test
    public void whenEntityHasNullStateThenReturnNull() {

        Project project = ProjectMapper.map(new ProjectEntity());

        Assertions.assertNull(project.getStatus());
    }
}
