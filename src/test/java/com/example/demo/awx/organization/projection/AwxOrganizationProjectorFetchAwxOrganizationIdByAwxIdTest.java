package com.example.demo.awx.organization.projection;

import com.example.demo.awx.organization.entity.model.AwxOrganization;
import com.example.demo.awx.organization.projection.model.FetchAwxOrganizationIdByAwxIdQuery;
import com.example.demo.awx.organization.projection.model.FetchAwxOrganizationIdByAwxIdResponse;
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
public class AwxOrganizationProjectorFetchAwxOrganizationIdByAwxIdTest {

    @Autowired
    private AwxOrganizationProjector awxOrganizationProjector;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenQueryIsValidThenReturnId() {

        AwxOrganization awxOrganization = sampleBuilder.builder()
                .awxOrganization()
                .build()
                .getAwxOrganization();

        FetchAwxOrganizationIdByAwxIdQuery query = new FetchAwxOrganizationIdByAwxIdQuery(awxOrganization.getOrganizationId());
        FetchAwxOrganizationIdByAwxIdResponse response = awxOrganizationProjector.fetchAwxOrganizationIdByAwxId(query);

        Assertions.assertEquals(awxOrganization.getId(), response.getId());
    }

    @Test
    public void whenQueryHasInvalidAwxIdThenReturnNull() {

        FetchAwxOrganizationIdByAwxIdQuery query = new FetchAwxOrganizationIdByAwxIdQuery(1L);
        FetchAwxOrganizationIdByAwxIdResponse response = awxOrganizationProjector.fetchAwxOrganizationIdByAwxId(query);

        Assertions.assertNull(response);
    }

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> awxOrganizationProjector.fetchAwxOrganizationIdByAwxId(null));
    }

    @Test
    public void whenQueryHasNullAwxIdThenExpectException() {

        FetchAwxOrganizationIdByAwxIdQuery query = new FetchAwxOrganizationIdByAwxIdQuery(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxOrganizationProjector.fetchAwxOrganizationIdByAwxId(query));
    }
}
