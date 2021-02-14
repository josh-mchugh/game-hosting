package com.example.demo.web.project.projection.service;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import com.example.demo.web.project.projection.service.model.ProjectDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
public class ProjectProjectorServiceTest {

    @Autowired
    private IProjectProjectorService projectControllerService;

    @Autowired
    private SampleBuilder sampleBuilder;

    private SampleData data;

    @BeforeEach
    public void setup() {

        data = sampleBuilder.createDefault();
    }

    @Test
    public void whenGetProjectDetailsHasValidIdThenReturnProjectDetails() {

        ProjectDetails projectDetails = projectControllerService.getProjectDetails(data.getProject().getId().toString());

        Assertions.assertNotNull(projectDetails);
    }

    @Test
    public void whenGetProjectDetailsHasNullIdThenThrowException() {

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> projectControllerService.getProjectDetails(null));

        Assertions.assertNotNull(exception);
    }

    @Test
    public void whenGetProjectDetailsHasInvalidIdThenThrowException() {

        Exception exception = Assertions.assertThrows(NullPointerException.class, () -> projectControllerService.getProjectDetails("123"));

        Assertions.assertNotNull(exception);
    }
}
