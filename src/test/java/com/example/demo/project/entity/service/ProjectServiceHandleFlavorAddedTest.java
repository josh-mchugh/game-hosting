package com.example.demo.project.entity.service;

import com.example.demo.project.aggregate.event.ProjectFlavorAddedEvent;
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
public class ProjectServiceHandleFlavorAddedTest {

    @Autowired
    private IProjectService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> service.handleFlavorAdded(null));
    }

    @Test
    public void whenParamHasNullIdThenThrowException() {

        ProjectFlavorAddedEvent event = ProjectFlavorAddedEvent.builder()
                .id(null)
                .ovhFlavorId(UUID.randomUUID())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> service.handleFlavorAdded(event));
    }

    @Test
    public void whenParamHasNullFlavorIdThenThrowException() {

        ProjectFlavorAddedEvent event = ProjectFlavorAddedEvent.builder()
                .id(UUID.randomUUID())
                .ovhFlavorId(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> service.handleFlavorAdded(event));
    }

    @Test
    public void whenParamHasInvalidProjectIdThenThrowException() {

        ProjectFlavorAddedEvent event = ProjectFlavorAddedEvent.builder()
                .id(UUID.randomUUID())
                .ovhFlavorId(UUID.randomUUID())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> service.handleFlavorAdded(event));
    }

    @Test
    public void whenParamHasInvalidFlavorIdThenDoNotExpectException() {

        Project project = sampleBuilder.builder()
                .game()
                .user()
                .project()
                .build()
                .getProject();

        ProjectFlavorAddedEvent event = ProjectFlavorAddedEvent.builder()
                .id(project.getId())
                .ovhFlavorId(UUID.randomUUID())
                .build();

        Assertions.assertDoesNotThrow(() -> service.handleFlavorAdded(event));
    }

    @Test
    public void whenParamIsValidThenDoNotExpectException() {

        SampleData data = sampleBuilder.builder()
                .flavor()
                .game()
                .user()
                .project()
                .build();

        ProjectFlavorAddedEvent event = ProjectFlavorAddedEvent.builder()
                .id(data.getProject().getId())
                .ovhFlavorId(data.getFlavor().getId())
                .build();

        Assertions.assertDoesNotThrow(() -> service.handleFlavorAdded(event));
    }

    @Test
    public void whenParaHasStateThenExpectState() {

        SampleData data = sampleBuilder.builder()
                .flavor()
                .game()
                .user()
                .project()
                .build();

        ProjectFlavorAddedEvent event = ProjectFlavorAddedEvent.builder()
                .id(data.getProject().getId())
                .ovhFlavorId(data.getFlavor().getId())
                .state(ProjectState.CONFIG_BILLING)
                .build();

        Project project = service.handleFlavorAdded(event);

        Assertions.assertEquals(ProjectState.CONFIG_BILLING, project.getState());
    }
}
