package com.example.demo.framework.seed.awx.project.projection;

import com.example.demo.awx.credential.entity.model.AwxCredential;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxCredentialByNameQuery;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxCredentialByNameResponse;
import com.example.demo.framework.seed.awx.project.projection.projection.AwxCredentialProjection;
import com.example.demo.sample.SampleBuilder;
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
public class AwxProjectSeedProjectionServiceFetchAwxCredentialByNameTest {

    @Autowired
    private IAwxProjectSeedProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchAwxCredentialIdByName(null));
    }

    @Test
    public void whenParamHasNullNameThenThrowException() {

        FetchAwxCredentialByNameQuery query = new FetchAwxCredentialByNameQuery(null);

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchAwxCredentialIdByName(query));
    }

    @Test
    public void whenAwxCredentialExistsThenReturnProjection() {

        AwxCredential awxCredential = sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .build()
                .getAwxCredential();

        FetchAwxCredentialByNameQuery query = new FetchAwxCredentialByNameQuery(awxCredential.getName());
        FetchAwxCredentialByNameResponse response = service.fetchAwxCredentialIdByName(query);

        AwxCredentialProjection expected = new AwxCredentialProjection(awxCredential.getId().toString(), awxCredential.getAwxId());
        Assertions.assertEquals(expected, response.getProjection());
    }

    @Test
    public void whenAwxCredentialDoesNotExistsThenReturnNull() {

        FetchAwxCredentialByNameQuery query = new FetchAwxCredentialByNameQuery("ANSIBLE");
        FetchAwxCredentialByNameResponse response = service.fetchAwxCredentialIdByName(query);

        Assertions.assertNull(response.getProjection());
    }
}
