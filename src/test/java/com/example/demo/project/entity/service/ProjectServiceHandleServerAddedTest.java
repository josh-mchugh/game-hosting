package com.example.demo.project.entity.service;

import com.example.demo.project.aggregate.event.ProjectServerAddedEvent;
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
public class ProjectServiceHandleServerAddedTest {

    @Autowired
    private IProjectService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> service.handleServerAdded(null));
    }

    @Test
    public void whenParamHasNullIdThenThrowException() {

        ProjectServerAddedEvent event = ProjectServerAddedEvent.builder()
                .id(null)
                .ovhFlavorId(UUID.randomUUID())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> service.handleServerAdded(event));
    }

    @Test
    public void whenParamHasNullFlavorIdThenThrowException() {

        ProjectServerAddedEvent event = ProjectServerAddedEvent.builder()
                .id(UUID.randomUUID())
                .ovhFlavorId(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> service.handleServerAdded(event));
    }

    @Test
    public void whenParamHasInvalidProjectIdThenThrowException() {

        ProjectServerAddedEvent event = ProjectServerAddedEvent.builder()
                .id(UUID.randomUUID())
                .ovhFlavorId(UUID.randomUUID())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> service.handleServerAdded(event));
    }

    @Test
    public void whenParamHasInvalidFlavorIdThenDoNotExpectException() {

        SampleData data = sampleBuilder.builder()
                .game()
                .image()
                .user()
                .project()
                .build();

        ProjectServerAddedEvent event = ProjectServerAddedEvent.builder()
                .id(data.getProject().getId())
                .ovhFlavorId(UUID.randomUUID())
                .ovhImageId(data.getImage().getId())
                .build();

        Assertions.assertDoesNotThrow(() -> service.handleServerAdded(event));
    }

    @Test
    public void whenParamHasInvalidImageIdThenDoNotExpectException() {

        SampleData data = sampleBuilder.builder()
                .flavor()
                .game()
                .user()
                .project()
                .build();

        ProjectServerAddedEvent event = ProjectServerAddedEvent.builder()
                .id(data.getProject().getId())
                .ovhFlavorId(data.getFlavor().getId())
                .ovhImageId(UUID.randomUUID())
                .build();

        Assertions.assertDoesNotThrow(() -> service.handleServerAdded(event));
    }

    @Test
    public void whenParamIsValidThenDoNotExpectException() {

        SampleData data = sampleBuilder.builder()
                .flavor()
                .image()
                .game()
                .user()
                .project()
                .build();

        ProjectServerAddedEvent event = ProjectServerAddedEvent.builder()
                .id(data.getProject().getId())
                .ovhFlavorId(data.getFlavor().getId())
                .ovhImageId(data.getImage().getId())
                .build();

        Assertions.assertDoesNotThrow(() -> service.handleServerAdded(event));
    }

    @Test
    public void whenParaHasStateThenExpectState() {

        SampleData data = sampleBuilder.builder()
                .flavor()
                .image()
                .game()
                .user()
                .project()
                .build();

        ProjectServerAddedEvent event = ProjectServerAddedEvent.builder()
                .id(data.getProject().getId())
                .ovhFlavorId(data.getFlavor().getId())
                .ovhImageId(data.getImage().getId())
                .state(ProjectState.CONFIG_BILLING)
                .build();

        Project project = service.handleServerAdded(event);

        Assertions.assertEquals(ProjectState.CONFIG_BILLING, project.getState());
    }
}
