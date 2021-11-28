package com.example.demo.project.entity.service;

import com.example.demo.project.aggregate.event.ProjectStateCreateInstanceUpdatedEvent;
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
public class ProjectServiceHandleStateCreateInstanceUpdatedTest {

    @Autowired
    private ProjectService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> service.handleStateInstanceBuildUpdated(null));
    }

    @Test
    public void whenParamHasNullIdThenThrowException() {

        ProjectStateCreateInstanceUpdatedEvent event = ProjectStateCreateInstanceUpdatedEvent.builder()
                .id(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> service.handleStateInstanceBuildUpdated(event));
    }

    @Test
    public void whenParamHasInvalidProjectIdThenThrowException() {

        ProjectStateCreateInstanceUpdatedEvent event = ProjectStateCreateInstanceUpdatedEvent.builder()
                .id(UUID.randomUUID())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> service.handleStateInstanceBuildUpdated(event));
    }

    @Test
    public void whenParamHasStateThenReturnState() {

        SampleData data = sampleBuilder.builder()
                .project()
                .configProjectRegion()
                .configProjectServer()
                .build();

        ProjectStateCreateInstanceUpdatedEvent event = ProjectStateCreateInstanceUpdatedEvent.builder()
                .id(data.getProject().getId())
                .status(ProjectStatus.BUILD)
                .state(ProjectState.BUILD_CREATE_INSTANCE)
                .build();

        Project project = service.handleStateInstanceBuildUpdated(event);

        Assertions.assertEquals(ProjectState.BUILD_CREATE_INSTANCE, project.getState());
    }

    @Test
    public void whenParamHasStatusThenReturnStatus() {

        SampleData data = sampleBuilder.builder()
                .project()
                .configProjectRegion()
                .configProjectServer()
                .configProjectBilling()
                .build();

        ProjectStateCreateInstanceUpdatedEvent event = ProjectStateCreateInstanceUpdatedEvent.builder()
                .id(data.getProject().getId())
                .status(ProjectStatus.BUILD)
                .state(ProjectState.BUILD_CREATE_INSTANCE)
                .build();

        Project project = service.handleStateInstanceBuildUpdated(event);

        Assertions.assertEquals(ProjectStatus.BUILD, project.getStatus());
    }
}
