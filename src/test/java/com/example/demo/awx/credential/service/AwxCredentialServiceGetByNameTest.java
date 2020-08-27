package com.example.demo.awx.credential.service;

import com.example.demo.awx.credential.model.AwxCredential;
import com.example.demo.awx.credential.service.model.AwxCredentialCreateRequest;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.util.TestAwxCredentialCreateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxCredentialServiceGetByNameTest {

    @Autowired
    private IAwxCredentialService awxCredentialService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @BeforeEach
    public void setup() {

        sampleBuilder.builder()
                .awxOrganization()
                .build();
    }

    @Test
    public void whenEntityExistsThenGetByNameReturnNotNull() {

        AwxCredentialCreateRequest request = TestAwxCredentialCreateRequest.builder()
                .name("test name")
                .build();
        awxCredentialService.handleAwxCredentialCreate(request);

        AwxCredential awxCredential = awxCredentialService.getByName("test name");

        Assertions.assertNotNull(awxCredential);
    }

    @Test
    public void whenEntityDoesNotExistThenGetByNameReturnNull() {

        AwxCredential awxCredential = awxCredentialService.getByName("name");

        Assertions.assertNull(awxCredential);
    }

    @Test
    public void whenRequestParamIsNullThenThrowException() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxCredentialService.getByName(null));
    }
}
