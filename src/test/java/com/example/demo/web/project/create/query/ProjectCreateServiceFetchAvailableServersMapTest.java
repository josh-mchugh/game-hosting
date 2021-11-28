package com.example.demo.web.project.create.query;

import com.example.demo.project.entity.model.Project;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import com.example.demo.web.project.create.query.model.FetchProjectAvailableServersMapQuery;
import com.example.demo.web.project.create.query.model.FetchProjectAvailableServersMapResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ProjectCreateServiceFetchAvailableServersMapTest {

    @Autowired
    private ProjectCreateService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> service.fetchAvailableServersMap(null));
    }

    @Test
    public void whenParaHasNullIdThenExpectException() {

        FetchProjectAvailableServersMapQuery query = new FetchProjectAvailableServersMapQuery(null);

        Assertions.assertThrows(NullPointerException.class, () -> service.fetchAvailableServersMap(query));
    }

    @Test
    public void whenRegionIsAvailableThenExpectResults() {

        SampleData sampleData = sampleBuilder.builder()
                .region()
                .flavor()
                .image()
                .game()
                .gameServer()
                .user()
                .project()
                .configProjectRegion()
                .build();

        FetchProjectAvailableServersMapQuery query = new FetchProjectAvailableServersMapQuery(sampleData.getProject().getId());
        FetchProjectAvailableServersMapResponse response = service.fetchAvailableServersMap(query);

        Assertions.assertEquals(1, response.getAvailableServers().size());
    }

    @Test
    public void whenRegionIsNotAvailableThenExpectEmptyResults() {

        Project project = sampleBuilder.builder()
                .user()
                .project()
                .build()
                .getProject();

        FetchProjectAvailableServersMapQuery query = new FetchProjectAvailableServersMapQuery(project.getId());
        FetchProjectAvailableServersMapResponse response = service.fetchAvailableServersMap(query);

        Assertions.assertEquals(0, response.getAvailableServers().size());
    }
}
