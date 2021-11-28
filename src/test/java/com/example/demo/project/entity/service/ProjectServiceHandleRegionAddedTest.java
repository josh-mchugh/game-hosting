package com.example.demo.project.entity.service;

import com.example.demo.project.aggregate.event.ProjectRegionAddedEvent;
import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.model.Project;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ProjectServiceHandleRegionAddedTest {

    @Autowired
    private ProjectService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> service.handleRegionAdded(null));
    }

    @Test
    public void whenParamHasNullIdThenExpectException() {

        ProjectRegionAddedEvent event = ProjectRegionAddedEvent.builder()
                .id(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> service.handleRegionAdded(event));
    }

    @Test
    public void whenParamHasNullOvhRegionIdThenExpectException() {

        Project project = sampleBuilder.builder()
                .game()
                .user()
                .project()
                .build()
                .getProject();

        ProjectRegionAddedEvent event = ProjectRegionAddedEvent.builder()
                .id(project.getId())
                .ovhRegionId(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> service.handleRegionAdded(event));
    }

    @Test
    public void whenParamHasInvalidProjectIdThenExpectException() {

        ProjectRegionAddedEvent event = ProjectRegionAddedEvent.builder()
                .id(UUID.randomUUID())
                .ovhRegionId(UUID.randomUUID())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> service.handleRegionAdded(event));
    }

    @Test
    public void whenParamHasInvalidRegionIdThenDoNotExpectException() {

        Project project = sampleBuilder.builder()
                .game()
                .user()
                .project()
                .build()
                .getProject();

        ProjectRegionAddedEvent event = ProjectRegionAddedEvent.builder()
                .id(project.getId())
                .ovhRegionId(UUID.randomUUID())
                .build();

        Assertions.assertDoesNotThrow(() -> service.handleRegionAdded(event));
    }

    @Test
    public void whenParamIsValidRegionIdThenDoNotExpectException() {

        SampleData data = sampleBuilder.builder()
                .region()
                .game()
                .user()
                .project()
                .build();

        ProjectRegionAddedEvent event = ProjectRegionAddedEvent.builder()
                .id(data.getProject().getId())
                .ovhRegionId(data.getRegion().getId())
                .build();

        Assertions.assertDoesNotThrow(() -> service.handleRegionAdded(event));
    }

    @Test
    public void whenParamHasStateThenReturnState() {

        SampleData data = sampleBuilder.builder()
                .region()
                .game()
                .user()
                .project()
                .build();

        ProjectRegionAddedEvent event = ProjectRegionAddedEvent.builder()
                .id(data.getProject().getId())
                .ovhRegionId(data.getRegion().getId())
                .state(ProjectState.CONFIG_SERVER)
                .build();

        Project project = service.handleRegionAdded(event);

        Assertions.assertEquals(ProjectState.CONFIG_SERVER, project.getState());
    }
}
