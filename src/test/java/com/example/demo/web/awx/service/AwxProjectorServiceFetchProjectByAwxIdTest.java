package com.example.demo.web.awx.service;

import com.example.demo.awx.project.entity.model.AwxProject;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.web.awx.service.model.FetchProjectByAwxIdQuery;
import com.example.demo.web.awx.service.model.FetchProjectByAwxIdResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.lang.reflect.UndeclaredThrowableException;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxProjectorServiceFetchProjectByAwxIdTest {

    @Autowired
    private IAwxProjectorService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectNoException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.getProjectByAwxId(null));
    }

    @Test
    public void whenParamHasNullAwxIdThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.getProjectByAwxId(new FetchProjectByAwxIdQuery(null)));
    }

    @Test
    public void whenProjectExistsThenExpectId() {

        AwxProject awxProject = sampleBuilder.builder()
                .awxOrganization()
                .awxGitlabCredential()
                .awxProject()
                .build()
                .getAwxProject();

        FetchProjectByAwxIdQuery query = new FetchProjectByAwxIdQuery(awxProject.getAwxId());
        FetchProjectByAwxIdResponse response = service.getProjectByAwxId(query);

        Assertions.assertEquals(awxProject.getId(), response.getProject().getId());
    }

    @Test
    public void whenProjectExistsThenExpectAwxId() {

        AwxProject awxProject = sampleBuilder.builder()
                .awxOrganization()
                .awxGitlabCredential()
                .awxProject()
                .build()
                .getAwxProject();

        FetchProjectByAwxIdQuery query = new FetchProjectByAwxIdQuery(awxProject.getAwxId());
        FetchProjectByAwxIdResponse response = service.getProjectByAwxId(query);

        Assertions.assertEquals(awxProject.getAwxId(), response.getProject().getAwxId());
    }

    @Test
    public void whenProjectDoesNotExistsThenExpectNull() {

        FetchProjectByAwxIdQuery query = new FetchProjectByAwxIdQuery(1L);
        FetchProjectByAwxIdResponse response = service.getProjectByAwxId(query);

        Assertions.assertNull(response.getProject());
    }
}
