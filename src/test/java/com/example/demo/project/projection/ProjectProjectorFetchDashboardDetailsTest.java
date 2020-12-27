package com.example.demo.project.projection;

import com.example.demo.project.projection.model.ProjectDashboardProjection;
import com.example.demo.project.projection.model.FetchProjectDashboardProjection;
import com.example.demo.project.projection.model.FetchProjectDashboardQuery;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ProjectProjectorFetchDashboardDetailsTest {

    @Autowired
    private IProjectProjector projectProjector;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> projectProjector.fetchProjectDashboard(null));
    }

    @Test
    public void whenQueryHasNullEmailThenThrowException() {

        FetchProjectDashboardQuery query = new FetchProjectDashboardQuery(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> projectProjector.fetchProjectDashboard(query));
    }

    @Test
    public void whenQueryHasInvalidEmailThenExpectEmptyList() {

        FetchProjectDashboardQuery query = new FetchProjectDashboardQuery("invalidEmail");

        FetchProjectDashboardProjection projection = projectProjector.fetchProjectDashboard(query);

        Assertions.assertEquals(ImmutableList.of(), projection.getProjects());
    }

    @Test
    public void whenQueryHasValidThenExpectList() {

        SampleData data = sampleBuilder.builder()
                .user()
                .game()
                .project()
                .build();

        FetchProjectDashboardQuery query = new FetchProjectDashboardQuery(data.getUser().getEmail());
        FetchProjectDashboardProjection projection = projectProjector.fetchProjectDashboard(query);

        ProjectDashboardProjection dashboardDetail = new ProjectDashboardProjection(data.getProject().getId(), data.getProject().getName(), data.getGame().getType());
        ImmutableList<ProjectDashboardProjection> expected = ImmutableList.of(dashboardDetail);

        Assertions.assertEquals(expected, projection.getProjects());
    }
}
