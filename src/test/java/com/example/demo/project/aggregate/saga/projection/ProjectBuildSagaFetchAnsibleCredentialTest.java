package com.example.demo.project.aggregate.saga.projection;

import com.example.demo.ovh.credential.entity.model.Credential;
import com.example.demo.project.aggregate.saga.projection.model.FetchAnsibleCredentialQuery;
import com.example.demo.project.aggregate.saga.projection.model.FetchAnsibleCredentialResponse;
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
public class ProjectBuildSagaFetchAnsibleCredentialTest {

    @Autowired
    private ProjectBuildProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectNoException() {

        Assertions.assertDoesNotThrow(() -> service.fetchAnsibleCredential(null));
    }

    @Test
    public void whenCredentialExistsThenReturnCredentialId() {

        Credential credential = sampleBuilder.builder()
                .credential()
                .build()
            .getCredential();

        FetchAnsibleCredentialQuery query = new FetchAnsibleCredentialQuery();
        FetchAnsibleCredentialResponse response = service.fetchAnsibleCredential(query);

        Assertions.assertEquals(credential.getId(), response.getId());
    }

    @Test
    public void whenCredentialExistsThenReturnCredentialOvhId() {

        Credential credential = sampleBuilder.builder()
                .credential()
                .build()
                .getCredential();

        FetchAnsibleCredentialQuery query = new FetchAnsibleCredentialQuery();
        FetchAnsibleCredentialResponse response = service.fetchAnsibleCredential(query);

        Assertions.assertEquals(credential.getSshKeyId(), response.getOvhId());
    }

    @Test
    public void whenCredentialDoesNotExistsThenReturnNull() {

        FetchAnsibleCredentialQuery query = new FetchAnsibleCredentialQuery();
        FetchAnsibleCredentialResponse response = service.fetchAnsibleCredential(query);

        Assertions.assertNull(response);
    }
}
