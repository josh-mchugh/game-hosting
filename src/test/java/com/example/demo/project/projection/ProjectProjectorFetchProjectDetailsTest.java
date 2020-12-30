package com.example.demo.project.projection;

import com.example.demo.project.entity.model.Project;
import com.example.demo.project.projection.model.FetchProjectDetailsByIdProjection;
import com.example.demo.project.projection.model.FetchProjectDetailsByIdQuery;
import com.example.demo.sample.SampleBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ProjectProjectorFetchProjectDetailsTest {

    @Autowired
    private IProjectProjector projectProjector;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenQueryIsNullThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> projectProjector.fetchProjectDetails(null));
    }

    @Test
    public void whenQueryHasNullIdThenThrowException() {

        FetchProjectDetailsByIdQuery query = new FetchProjectDetailsByIdQuery(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> projectProjector.fetchProjectDetails(query));
    }

    @Test
    public void whenQueryHasInvalidIdThenReturnNull() {

        FetchProjectDetailsByIdQuery query = new FetchProjectDetailsByIdQuery("invalidId");

        Assertions.assertNull(projectProjector.fetchProjectDetails(query));
    }

    @Test
    public void whenQueryIsValidThenReturnProjectDetails() {

        Project project = sampleBuilder.builder()
                .user()
                .game()
                .project()
                .build()
                .getProject();

        FetchProjectDetailsByIdQuery query = new FetchProjectDetailsByIdQuery(project.getId());

        FetchProjectDetailsByIdProjection projection = projectProjector.fetchProjectDetails(query);

        Assertions.assertEquals(project.getName(), projection.getName());
    }
}
