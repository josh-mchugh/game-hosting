package com.example.demo.framework.seed.ovh.credential.projection;

import com.example.demo.framework.seed.ovh.credential.projection.model.ExistsAnyCredentialQuery;
import com.example.demo.framework.seed.ovh.credential.projection.model.ExistsAnyCredentialResponse;
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
public class CredentialSeedProjectionServiceExistsAnyTest {

    @Autowired
    private ICredentialSeedProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectNoException() {

        Assertions.assertDoesNotThrow(() -> service.existsAny(null));
    }

    @Test
    public void whenCredentialsExistsThenReturnTrue() {

        sampleBuilder.builder()
                .credential()
                .build();

        ExistsAnyCredentialQuery query = new ExistsAnyCredentialQuery();
        ExistsAnyCredentialResponse response = service.existsAny(query);

        Assertions.assertTrue(response.exists());
    }

    @Test
    public void whenCredentialDoesNotExistsThenReturnFalse() {

        ExistsAnyCredentialQuery query = new ExistsAnyCredentialQuery();
        ExistsAnyCredentialResponse response = service.existsAny(query);

        Assertions.assertFalse(response.exists());
    }
}
