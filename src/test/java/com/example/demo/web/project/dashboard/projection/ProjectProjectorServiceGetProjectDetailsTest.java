package com.example.demo.web.project.dashboard.projection;

import com.example.demo.project.entity.model.Project;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.web.project.dashboard.projection.model.FetchProjectDetailsQuery;
import com.example.demo.web.project.dashboard.projection.model.FetchProjectDetailsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.lang.reflect.UndeclaredThrowableException;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
public class ProjectProjectorServiceGetProjectDetailsTest {

    @Autowired
    private ProjectDashboardService projectControllerService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenGetProjectDetailsHasValidIdThenReturnProjectDetails() {

        Project project = sampleBuilder.createDefault().getProject();

        FetchProjectDetailsQuery query = new FetchProjectDetailsQuery(project.getId().toString());
        FetchProjectDetailsResponse projectDetails = projectControllerService.getProjectDetails(query);

        Assertions.assertNotNull(projectDetails);
    }

    @Test
    public void whenGetProjectDetailsHasNullIdThenThrowException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> projectControllerService.getProjectDetails(null));
    }

    @Test
    public void whenGetProjectDetailsHasInvalidIdThenThrowException() {

        FetchProjectDetailsQuery query = new FetchProjectDetailsQuery("123");
        Assertions.assertThrows(UndeclaredThrowableException.class, () -> projectControllerService.getProjectDetails(query));
    }
}
