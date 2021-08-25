package com.example.demo.web.project.create.query;


import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import com.example.demo.project.entity.model.Project;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.web.project.create.query.model.FetchProjectStatusAndStateQuery;
import com.example.demo.web.project.create.query.model.FetchProjectStatusAndStateResponse;
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
public class ProjectCreateProjectionServiceFetchStatusAndStateTest {

    @Autowired
    private ProjectCreateQueryService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> service.fetchStatusAndState(null));
    }

    @Test
    public void whenParamHasNullIdThenExpectException() {

        FetchProjectStatusAndStateQuery query = new FetchProjectStatusAndStateQuery(null);

        Assertions.assertThrows(NullPointerException.class, () -> service.fetchStatusAndState(query));
    }

    @Test
    public void whenProjectExistsThenReturnStatus() {

        Project project = sampleBuilder.builder()
                .project()
                .configProjectRegion()
                .build()
                .getProject();

        FetchProjectStatusAndStateQuery query = new FetchProjectStatusAndStateQuery(project.getId());
        FetchProjectStatusAndStateResponse response = service.fetchStatusAndState(query);

        Assertions.assertEquals(ProjectStatus.CONFIG, response.getStatus());
    }

    @Test
    public void whenProjectExistsThenReturnState() {

        Project project = sampleBuilder.builder()
                .project()
                .configProjectRegion()
                .build()
                .getProject();

        FetchProjectStatusAndStateQuery query = new FetchProjectStatusAndStateQuery(project.getId());
        FetchProjectStatusAndStateResponse response = service.fetchStatusAndState(query);

        Assertions.assertEquals(ProjectState.CONFIG_SERVER, response.getState());
    }

    @Test
    public void whenProjectDoesNotExistThenExpectNullStatus() {

        FetchProjectStatusAndStateQuery query = new FetchProjectStatusAndStateQuery(UUID.randomUUID());
        FetchProjectStatusAndStateResponse response = service.fetchStatusAndState(query);

        Assertions.assertNull(response.getStatus());
    }

    @Test
    public void whenProjectDoesNotExistThenExpectNullState() {

        FetchProjectStatusAndStateQuery query = new FetchProjectStatusAndStateQuery(UUID.randomUUID());
        FetchProjectStatusAndStateResponse response = service.fetchStatusAndState(query);

        Assertions.assertNull(response.getState());
    }
}
