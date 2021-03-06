package com.example.demo.project.aggregate.saga;

import com.example.demo.project.aggregate.saga.model.FetchAnsibleCredentialQuery;
import com.example.demo.project.aggregate.saga.model.FetchAnsibleCredentialResponse;
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
public class ProjectCreatedSagaProjectionServiceFetchAnsibleCredentialTest {

    @Autowired
    private IProjectCreatedSagaProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectNoErrorThrown() {

        Assertions.assertDoesNotThrow(() -> service.fetchAnsibleCredential(null));
    }

    @Test
    public void whenCredentialExistsThenReturnOvhId() {

        sampleBuilder.builder()
                .credential()
                .build();

        FetchAnsibleCredentialQuery query = new FetchAnsibleCredentialQuery();
        FetchAnsibleCredentialResponse response = service.fetchAnsibleCredential(query);

        Assertions.assertEquals("ovhId", response.getOvhId());
    }

    @Test
    public void whenCredentialDoesNotExistThenReturnOvhId() {

        FetchAnsibleCredentialQuery query = new FetchAnsibleCredentialQuery();
        FetchAnsibleCredentialResponse response = service.fetchAnsibleCredential(query);

        Assertions.assertNull(response.getOvhId());
    }
}
