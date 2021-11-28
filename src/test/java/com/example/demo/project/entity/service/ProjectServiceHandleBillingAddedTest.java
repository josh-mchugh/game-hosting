package com.example.demo.project.entity.service;

import com.example.demo.project.aggregate.event.ProjectBillingAddedEvent;
import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
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
public class ProjectServiceHandleBillingAddedTest {

    @Autowired
    private ProjectService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> service.handleBillingAdded(null));
    }

    @Test
    public void whenParamHasNullIdThenThrowException() {

        ProjectBillingAddedEvent event = ProjectBillingAddedEvent.builder()
                .id(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> service.handleBillingAdded(event));
    }

    @Test
    public void whenParamHasInvalidProjectIdThenThrowException() {

        ProjectBillingAddedEvent event = ProjectBillingAddedEvent.builder()
                .id(UUID.randomUUID())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> service.handleBillingAdded(event));
    }

    @Test
    public void whenParamHasStateThenReturnState() {

        SampleData data = sampleBuilder.builder()
                .project()
                .configProjectRegion()
                .build();

        ProjectBillingAddedEvent event = ProjectBillingAddedEvent.builder()
                .id(data.getProject().getId())
                .state(ProjectState.BUILD_CREATE_INSTANCE_GROUP)
                .build();

        Project project = service.handleBillingAdded(event);

        Assertions.assertEquals(ProjectState.BUILD_CREATE_INSTANCE_GROUP, project.getState());
    }

    @Test
    public void whenParamHasStatusThenReturnStatus() {

        SampleData data = sampleBuilder.builder()
                .project()
                .configProjectRegion()
                .build();

        ProjectBillingAddedEvent event = ProjectBillingAddedEvent.builder()
                .id(data.getProject().getId())
                .status(ProjectStatus.BUILD)
                .build();

        Project project = service.handleBillingAdded(event);

        Assertions.assertEquals(ProjectStatus.BUILD, project.getStatus());
    }
}
