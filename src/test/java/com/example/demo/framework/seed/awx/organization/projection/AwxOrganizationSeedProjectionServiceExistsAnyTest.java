package com.example.demo.framework.seed.awx.organization.projection;

import com.example.demo.framework.seed.awx.organization.projection.model.ExistsAnyAwxOrganizationQuery;
import com.example.demo.framework.seed.awx.organization.projection.model.ExistsAnyAwxOrganizationResponse;
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
public class AwxOrganizationSeedProjectionServiceExistsAnyTest {

    @Autowired
    private IAwxOrganizationSeedProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectNoExpect() {

        Assertions.assertDoesNotThrow(() -> service.existsAny(null));
    }

    @Test
    public void whenAwxOrganizationExistsThenReturnTrue() {

        sampleBuilder.builder().awxOrganization().build();

        ExistsAnyAwxOrganizationQuery query = new ExistsAnyAwxOrganizationQuery();
        ExistsAnyAwxOrganizationResponse response = service.existsAny(query);

        Assertions.assertTrue(response.exists());
    }

    @Test
    public void whenAwxOrganizationDoesNotExistsThenReturnFalse() {

        ExistsAnyAwxOrganizationQuery query = new ExistsAnyAwxOrganizationQuery();
        ExistsAnyAwxOrganizationResponse response = service.existsAny(query);

        Assertions.assertFalse(response.exists());
    }
}
