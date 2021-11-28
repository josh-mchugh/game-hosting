package com.example.demo.project.aggregate.saga.projection;

import com.example.demo.project.aggregate.saga.projection.model.FetchProjectRegionNameByIdQuery;
import com.example.demo.project.aggregate.saga.projection.model.FetchProjectRegionNameByIdResponse;
import com.example.demo.project.entity.model.Project;
import com.example.demo.sample.SampleBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ProjectBuildSagaServiceFetchProjectRegionNameByIdTest {

    @Autowired
    private ProjectBuildProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchProjectRegionNameById(null));
    }

    @Test
    public void whenParamHasNullIdThenExpectException() {

        FetchProjectRegionNameByIdQuery query = new FetchProjectRegionNameByIdQuery(null);

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchProjectRegionNameById(query));
    }

    @Test
    public void whenProjectExistsThenExpectRegionName() {

        Project project = sampleBuilder.builder()
                .project()
                .configProjectRegion()
                .build()
                .getProject();

        FetchProjectRegionNameByIdQuery query = new FetchProjectRegionNameByIdQuery(project.getId());

        FetchProjectRegionNameByIdResponse response = service.fetchProjectRegionNameById(query);

        Assertions.assertEquals("US-EAST-VA-1", response.getRegionName());
    }

    @Test
    public void whenProjectDoesNotExistsThenExpectNull() {

        FetchProjectRegionNameByIdQuery query = new FetchProjectRegionNameByIdQuery(UUID.randomUUID());

        FetchProjectRegionNameByIdResponse response = service.fetchProjectRegionNameById(query);

        Assertions.assertNull(response.getRegionName());
    }
}
