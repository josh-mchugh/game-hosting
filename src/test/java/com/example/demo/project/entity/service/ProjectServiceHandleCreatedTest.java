package com.example.demo.project.entity.service;

import com.example.demo.project.aggregate.event.ProjectCreatedEvent;
import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import com.example.demo.project.entity.model.Project;
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
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ProjectServiceHandleCreatedTest {

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
    public void whenParamIsNullThenExpectException() {

        Exception exception = Assertions.assertThrows(NullPointerException.class, () -> projectService.handleCreated(null));

        Assertions.assertNotNull(exception);
    }

    @Test
    public void whenEventIsValidThenExpectNotNull() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("name")
                .gameId(data.getGame().getId())
                .state(ProjectState.ACTIVE)
                .status(ProjectStatus.ACTIVE)
                .member(ProjectCreatedEvent.createOwner(data.getUser().getId()))
                .build();

        Project project = projectService.handleCreated(event);

        Assertions.assertNotNull(project);
    }

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(id)
                .name("name")
                .gameId(data.getGame().getId())
                .state(ProjectState.ACTIVE)
                .status(ProjectStatus.ACTIVE)
                .member(ProjectCreatedEvent.createOwner(data.getUser().getId()))
                .build();

        Project project = projectService.handleCreated(event);

        Assertions.assertEquals(id, project.getId());
    }

    @Test
    public void whenEventHasNullIdThenExpectException() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(null)
                .name("name")
                .gameId(data.getGame().getId())
                .member(ProjectCreatedEvent.createOwner(data.getUser().getId()))
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> projectService.handleCreated(event));
    }

    @Test
    public void whenEventHasNameThenReturnName() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("name")
                .gameId(data.getGame().getId())
                .state(ProjectState.ACTIVE)
                .status(ProjectStatus.ACTIVE)
                .member(ProjectCreatedEvent.createOwner(data.getUser().getId()))
                .build();

        Project project = projectService.handleCreated(event);

        Assertions.assertEquals("name", project.getName());
    }

    @Test
    public void whenEventHasNullNameThenThrowException() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name(null)
                .gameId(data.getGame().getId())
                .member(ProjectCreatedEvent.createOwner(data.getUser().getId()))
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> projectService.handleCreated(event));
    }

    @Test
    public void whenEventHasNullUserIdThenThrowException() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("name")
                .gameId(data.getGame().getId())
                .member(ProjectCreatedEvent.createOwner(null))
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> projectService.handleCreated(event));
    }

    @Test
    public void whenEventHasInvalidUserIdThenThrowException() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("name")
                .gameId(data.getGame().getId())
                .member(ProjectCreatedEvent.createOwner(UUID.randomUUID()))
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> projectService.handleCreated(event));
    }

    @Test
    public void whenEventHasNullGameIdThenThrowException() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("name")
                .gameId(null)
                .member(ProjectCreatedEvent.createOwner(data.getUser().getId()))
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> projectService.handleCreated(event));
    }

    @Test
    public void whenEventIsValidThenReturnActiveStatus() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("name")
                .gameId(data.getGame().getId())
                .state(ProjectState.ACTIVE)
                .status(ProjectStatus.ACTIVE)
                .member(ProjectCreatedEvent.createOwner(data.getUser().getId()))
                .build();

        Project project = projectService.handleCreated(event);

        Assertions.assertEquals(ProjectStatus.ACTIVE, project.getStatus());
    }

    @Test
    public void whenEventIsValidThenReturnBuildState() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("name")
                .state(ProjectState.BUILD_CREATE_INSTANCE_GROUP)
                .status(ProjectStatus.ACTIVE)
                .gameId(data.getGame().getId())
                .member(ProjectCreatedEvent.createOwner(data.getUser().getId()))
                .build();

        Project project = projectService.handleCreated(event);

        Assertions.assertEquals(ProjectState.BUILD_CREATE_INSTANCE_GROUP, project.getState());
    }

    @Test
    public void whenEventMemberIsNullThenThrowException() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("name")
                .gameId(data.getGame().getId())
                .member(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> projectService.handleCreated(event));
    }

    @Test
    public void whenEventMemberHasNullIdThenThrowException() {

        ProjectCreatedEvent.Member member = ProjectCreatedEvent.Member.builder()
                .id(null)
                .userId(data.getUser().getId())
                .build();

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("name")
                .gameId(data.getGame().getId())
                .member(member)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> projectService.handleCreated(event));
    }

    @Test
    public void whenEventMemberHasNullUserIdThenThrowException() {

        ProjectCreatedEvent.Member member = ProjectCreatedEvent.Member.builder()
                .id(UUID.randomUUID())
                .userId(null)
                .build();

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("name")
                .gameId(data.getGame().getId())
                .member(member)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> projectService.handleCreated(event));
    }
}
