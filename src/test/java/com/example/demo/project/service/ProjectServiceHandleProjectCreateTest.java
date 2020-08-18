package com.example.demo.project.service;

import com.example.demo.game.entity.GameType;
import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import com.example.demo.project.model.Project;
import com.example.demo.project.service.model.ProjectCreateRequest;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ProjectServiceHandleProjectCreateTest {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private SampleBuilder sampleBuilder;

    private SampleData data;

    @BeforeEach
    public void setup() {

        data = sampleBuilder.builder()
                .user()
                .game()
                .build();
    }

    @Test
    public void whenCreateRequestIsNullThenThrowException() {

        Exception exception = Assertions.assertThrows(NullPointerException.class, () -> projectService.handleProjectCreate(null));

        Assertions.assertNotNull(exception);
    }

    @Test
    public void whenCreateRequestIsValidReturnNonNull() {

        ProjectCreateRequest createRequest = ProjectCreateRequest.builder()
                .name("name")
                .userId(data.getUser().getId())
                .gameType(GameType.MINECRAFT_JAVA)
                .build();

        Project project = projectService.handleProjectCreate(createRequest);

        Assertions.assertNotNull(project);
    }

    @Test
    public void whenCreateRequestIsValidThenReturnId() {

        ProjectCreateRequest createRequest = ProjectCreateRequest.builder()
                .name("name")
                .userId(data.getUser().getId())
                .gameType(GameType.MINECRAFT_JAVA)
                .build();

        Project project = projectService.handleProjectCreate(createRequest);

        Assertions.assertNotNull(project.getId());
    }

    @Test
    public void whenCreateRequestHasNameThenReturnName() {

        ProjectCreateRequest createRequest = ProjectCreateRequest.builder()
                .name("name")
                .userId(data.getUser().getId())
                .gameType(GameType.MINECRAFT_JAVA)
                .build();

        Project project = projectService.handleProjectCreate(createRequest);

        Assertions.assertEquals("name", project.getName());
    }

    @Test
    public void whenCreateRequestHasNullNameThenThrowException() {

        ProjectCreateRequest createRequest = ProjectCreateRequest.builder()
                .userId(data.getUser().getId())
                .gameType(GameType.MINECRAFT_JAVA)
                .build();

        Exception exception = Assertions.assertThrows(PersistenceException.class, () -> projectService.handleProjectCreate(createRequest));

        Assertions.assertNotNull(exception);
    }

    @Test
    public void whenCreateRequestHasNullUserIdThenThrowException() {

        ProjectCreateRequest createRequest = ProjectCreateRequest.builder()
                .name("name")
                .build();

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> projectService.handleProjectCreate(createRequest));

        Assertions.assertNotNull(exception);
    }

    @Test
    public void whenCreateRequestHasInvalidUserIdThenThrowException() {

        ProjectCreateRequest createRequest = ProjectCreateRequest.builder()
                .name("name")
                .userId("invalid-user-id")
                .gameType(GameType.MINECRAFT_JAVA)
                .build();

        Exception exception = Assertions.assertThrows(PersistenceException.class, () -> projectService.handleProjectCreate(createRequest));

        Assertions.assertNotNull(exception);
    }

    @Test
    public void whenCreateRequestHasNullGameTypeThenThrowException() {

        ProjectCreateRequest createRequest = ProjectCreateRequest.builder()
                .name("name")
                .userId(data.getUser().getId())
                .build();

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> projectService.handleProjectCreate(createRequest));

        Assertions.assertNotNull(exception);
    }

    @Test
    public void whenCreateRequestIsValidThenReturnActiveStatus() {

        ProjectCreateRequest createRequest = ProjectCreateRequest.builder()
                .name("name")
                .userId(data.getUser().getId())
                .gameType(GameType.MINECRAFT_JAVA)
                .build();
        Project project = projectService.handleProjectCreate(createRequest);

        Assertions.assertEquals(ProjectStatus.ACTIVE, project.getStatus());
    }

    @Test
    public void whenCreateRequestIsValidThenReturnBuildState() {

        ProjectCreateRequest createRequest = ProjectCreateRequest.builder()
                .name("name")
                .userId(data.getUser().getId())
                .gameType(GameType.MINECRAFT_JAVA)
                .build();
        Project project = projectService.handleProjectCreate(createRequest);

        Assertions.assertEquals(ProjectState.BUILD, project.getState());
    }
}
