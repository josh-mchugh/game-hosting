package com.example.demo.project.service;

import com.example.demo.game.entity.GameType;
import com.example.demo.game.service.IGameService;
import com.example.demo.game.service.model.GameCreateRequest;
import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import com.example.demo.project.model.Project;
import com.example.demo.project.service.model.ProjectCreateRequest;
import com.example.demo.sample.TestGameUtil;
import com.example.demo.sample.TestUserUtil;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserCreateRequest;
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
    private IUserService userService;

    @Autowired
    private IGameService gameService;

    private User user;

    @BeforeEach
    public void setup() {

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("test@test");
        user = userService.handleCreateUser(userCreateRequest);

        GameCreateRequest gameCreateRequest = TestGameUtil.builder().build();
        gameService.handleGameCreateRequest(gameCreateRequest);
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
                .userId(user.getId())
                .gameType(GameType.MINECRAFT_JAVA)
                .build();

        Project project = projectService.handleProjectCreate(createRequest);

        Assertions.assertNotNull(project);
    }

    @Test
    public void whenCreateRequestIsValidThenReturnId() {

        ProjectCreateRequest createRequest = ProjectCreateRequest.builder()
                .name("name")
                .userId(user.getId())
                .gameType(GameType.MINECRAFT_JAVA)
                .build();

        Project project = projectService.handleProjectCreate(createRequest);

        Assertions.assertNotNull(project.getId());
    }

    @Test
    public void whenCreateRequestHasNameThenReturnName() {

        ProjectCreateRequest createRequest = ProjectCreateRequest.builder()
                .name("name")
                .userId(user.getId())
                .gameType(GameType.MINECRAFT_JAVA)
                .build();

        Project project = projectService.handleProjectCreate(createRequest);

        Assertions.assertEquals("name", project.getName());
    }

    @Test
    public void whenCreateRequestHasNullNameThenThrowException() {

        ProjectCreateRequest createRequest = ProjectCreateRequest.builder()
                .userId(user.getId())
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
                .userId(user.getId())
                .build();

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> projectService.handleProjectCreate(createRequest));

        Assertions.assertNotNull(exception);
    }

    @Test
    public void whenCreateRequestIsValidThenReturnActiveStatus() {

        ProjectCreateRequest createRequest = ProjectCreateRequest.builder()
                .name("name")
                .userId(user.getId())
                .gameType(GameType.MINECRAFT_JAVA)
                .build();
        Project project = projectService.handleProjectCreate(createRequest);

        Assertions.assertEquals(ProjectStatus.ACTIVE, project.getStatus());
    }

    @Test
    public void whenCreateRequestIsValidThenReturnBuildState() {

        ProjectCreateRequest createRequest = ProjectCreateRequest.builder()
                .name("name")
                .userId(user.getId())
                .gameType(GameType.MINECRAFT_JAVA)
                .build();
        Project project = projectService.handleProjectCreate(createRequest);

        Assertions.assertEquals(ProjectState.BUILD, project.getState());
    }
}
