package com.example.demo.framework.seed.awx.credential.projection;

import com.example.demo.framework.seed.awx.credential.projection.model.ExistsAnyAwxCredentialQuery;
import com.example.demo.framework.seed.awx.credential.projection.model.ExistsAnyAwxCredentialResponse;
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
public class AwxCredentialSeedProjectionServiceExistsAnyTest {

    @Autowired
    private AwxCredentialSeedProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectNoException() {

        Assertions.assertDoesNotThrow(() -> service.existsAny(null));
    }

    @Test
    public void whenAwxCredentialExistsThenReturnTrue() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxCredential();

        ExistsAnyAwxCredentialQuery query = new ExistsAnyAwxCredentialQuery();
        ExistsAnyAwxCredentialResponse response = service.existsAny(query);

        Assertions.assertTrue(response.exists());
    }

    @Test
    public void whenAwxCredentialDoesNotExistsThenReturnFalse() {

        ExistsAnyAwxCredentialQuery query = new ExistsAnyAwxCredentialQuery();
        ExistsAnyAwxCredentialResponse response = service.existsAny(query);

        Assertions.assertFalse(response.exists());
    }
}
