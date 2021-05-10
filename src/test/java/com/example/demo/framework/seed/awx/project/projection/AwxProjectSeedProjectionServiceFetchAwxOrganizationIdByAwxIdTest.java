package com.example.demo.framework.seed.awx.project.projection;

import com.example.demo.awx.organization.entity.model.AwxOrganization;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxOrganizationIdByAwxIdQuery;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxOrganizationIdByAwxIdResponse;
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
public class AwxProjectSeedProjectionServiceFetchAwxOrganizationIdByAwxIdTest {

    @Autowired
    private IAwxProjectSeedProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchAwxOrganizationIdByAwxId(null));
    }

    @Test
    public void whenQueryHasNullAwxIdThenExpectException() {

        FetchAwxOrganizationIdByAwxIdQuery query = new FetchAwxOrganizationIdByAwxIdQuery(null);

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchAwxOrganizationIdByAwxId(query));
    }

    @Test
    public void whenQueryIsValidThenReturnId() {

        AwxOrganization awxOrganization = sampleBuilder.builder()
                .awxOrganization()
                .build()
                .getAwxOrganization();

        FetchAwxOrganizationIdByAwxIdQuery query = new FetchAwxOrganizationIdByAwxIdQuery(awxOrganization.getAwxId());
        FetchAwxOrganizationIdByAwxIdResponse response = service.fetchAwxOrganizationIdByAwxId(query);

        Assertions.assertEquals(awxOrganization.getId(), response.getId());
    }

    @Test
    public void whenQueryHasInvalidAwxIdThenReturnNull() {

        FetchAwxOrganizationIdByAwxIdQuery query = new FetchAwxOrganizationIdByAwxIdQuery(1L);
        FetchAwxOrganizationIdByAwxIdResponse response = service.fetchAwxOrganizationIdByAwxId(query);

        Assertions.assertNull(response.getId());
    }
}