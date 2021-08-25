package com.example.demo.web.project.create.query;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.web.project.create.query.model.FetchProjectAvailableGameMapQuery;
import com.example.demo.web.project.create.query.model.FetchProjectAvailableGameMapResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
public class ProjectCreateProjectionServiceFetchAvailableGamesMapTest {

    @Autowired
    private ProjectCreateQueryService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullExpectNoExceptions() {

        Assertions.assertDoesNotThrow(() -> service.fetchAvailableGameMap(null));
    }

    @Test
    public void whenGameIsAvailableThenExpectResults() {

        sampleBuilder.builder()
                .region()
                .flavor()
                .image()
                .game()
                .gameServer()
                .build();

        FetchProjectAvailableGameMapQuery query = new FetchProjectAvailableGameMapQuery();
        FetchProjectAvailableGameMapResponse response = service.fetchAvailableGameMap(query);

        Assertions.assertEquals(1, response.getAvailableGames().size());
    }

    @Test
    public void whenGameIsNotAvailableThenExpectNoResults() {

        FetchProjectAvailableGameMapQuery query = new FetchProjectAvailableGameMapQuery();
        FetchProjectAvailableGameMapResponse response = service.fetchAvailableGameMap(query);

        Assertions.assertEquals(0, response.getAvailableGames().size());
    }
}
