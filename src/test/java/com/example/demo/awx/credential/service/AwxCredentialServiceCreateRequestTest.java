package com.example.demo.awx.credential.service;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import com.example.demo.awx.credential.model.AwxCredential;
import com.example.demo.awx.credential.service.model.AwxCredentialCreateRequest;
import com.example.demo.awx.organization.model.AwxOrganization;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.util.TestAwxCredentialCreateRequest;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxCredentialServiceCreateRequestTest {

    @Autowired
    private IAwxCredentialService awxCredentialService;

    @Autowired
    private StringEncryptor encryptor;

    @Autowired
    private SampleBuilder sampleBuilder;

    private AwxOrganization awxOrganization;

    @BeforeEach
    public void setup() {

        awxOrganization = sampleBuilder.builder()
                .awxOrganization()
                .build()
                .getAwxOrganization();
    }

    @Test
    public void whenCreateRequestIsValidThenReturnNotNull() {

        AwxCredentialCreateRequest request = TestAwxCredentialCreateRequest.createDefault();
        AwxCredential awxCredential = awxCredentialService.handleAwxCredentialCreate(request);

        Assertions.assertNotNull(awxCredential);
    }

    @Test
    public void whenCreateRequestIsValidThenReturnId() {

        AwxCredential awxCredential = sampleBuilder.builder()
                .awxCredential()
                .build()
                .getAwxCredential();

        Assertions.assertNotNull(awxCredential.getId());
    }

    @Test
    public void whenCreateRequestHasCredentialIdThenReturnCredentialId() {

        AwxCredentialCreateRequest request = TestAwxCredentialCreateRequest.builder()
                .credentialId(1L)
                .build();
        AwxCredential awxCredential = awxCredentialService.handleAwxCredentialCreate(request);

        Assertions.assertEquals(1L, awxCredential.getCredentialId());
    }

    @Test
    public void whenCreateRequestHasNullCredentialIdThenThrowException() {

        AwxCredentialCreateRequest request = TestAwxCredentialCreateRequest.builder()
                .credentialId(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () ->awxCredentialService.handleAwxCredentialCreate(request));
    }

    @Test
    public void whenCreateRequestHasOrganizationIdThenReturnNonNull() {

        AwxCredentialCreateRequest request = TestAwxCredentialCreateRequest.builder()
                .awxOrganizationId(awxOrganization)
                .build();
        AwxCredential awxCredential = awxCredentialService.handleAwxCredentialCreate(request);

        Assertions.assertNotNull(awxCredential);
    }

    @Test
    public void whenCreateRequestHasNullOrganizationIdThenThrowException() {

        AwxCredentialCreateRequest request = TestAwxCredentialCreateRequest.builder()
                .awxOrganizationId((Long) null)
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxCredentialService.handleAwxCredentialCreate(request));
    }

    @Test
    public void whenCreateRequestHasNameThenReturnName() {

        AwxCredentialCreateRequest request = TestAwxCredentialCreateRequest.builder()
                .name("name")
                .build();
        AwxCredential awxCredential = awxCredentialService.handleAwxCredentialCreate(request);

        Assertions.assertEquals("name", awxCredential.getName());
    }

    @Test
    public void whenCreateRequestHasNullNameThenThrowException() {

        AwxCredentialCreateRequest request = TestAwxCredentialCreateRequest.builder()
                .name(null)
                .build();
        Assertions.assertThrows(PersistenceException.class, () -> awxCredentialService.handleAwxCredentialCreate(request));
    }

    @Test
    public void whenCreateRequestHasDescriptionThenReturnDescription() {

        AwxCredentialCreateRequest request = TestAwxCredentialCreateRequest.builder()
                .description("description")
                .build();
        AwxCredential awxCredential = awxCredentialService.handleAwxCredentialCreate(request);

        Assertions.assertEquals("description", awxCredential.getDescription());
    }

    @Test
    public void whenCreateRequestHasNullDescriptionThenReturnNull() {

        AwxCredentialCreateRequest request = TestAwxCredentialCreateRequest.builder()
                .description(null)
                .build();
        AwxCredential awxCredential = awxCredentialService.handleAwxCredentialCreate(request);

        Assertions.assertNull(awxCredential.getDescription());
    }

    @Test
    public void whenCreateRequestHasPrivateKeyThenReturnPrivateKey() {

        AwxCredentialCreateRequest request = TestAwxCredentialCreateRequest.builder()
                .privateKey("private key")
                .build();
        AwxCredential awxCredential = awxCredentialService.handleAwxCredentialCreate(request);

        Assertions.assertEquals("private key", encryptor.decrypt(awxCredential.getPrivateKey()));
    }

    @Test
    public void whenCreateRequestHasNullPrivateKeyThenThrowException() {

        AwxCredentialCreateRequest request = TestAwxCredentialCreateRequest.builder()
                .privateKey(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxCredentialService.handleAwxCredentialCreate(request));
    }

    @Test
    public void whenCreateRequestHasPassphraseThenReturnPassphrase() {

        AwxCredentialCreateRequest request = TestAwxCredentialCreateRequest.builder()
                .passphrase("passphrase")
                .build();
        AwxCredential awxCredential = awxCredentialService.handleAwxCredentialCreate(request);

        Assertions.assertEquals("passphrase", encryptor.decrypt(awxCredential.getPassphrase()));
    }

    @Test
    public void whenCreateRequestHasNullPassphraseThenReturnNull() {

        AwxCredentialCreateRequest request = TestAwxCredentialCreateRequest.builder()
                .passphrase(null)
                .build();
        AwxCredential awxCredential = awxCredentialService.handleAwxCredentialCreate(request);

        Assertions.assertNull(awxCredential.getPassphrase());
    }
    
    @Test
    public void whenCreateRequestHasTypeThenReturnType() {

        AwxCredentialCreateRequest request = TestAwxCredentialCreateRequest.builder()
                .type(AwxCredentialType.MACHINE)
                .build();
        AwxCredential awxCredential = awxCredentialService.handleAwxCredentialCreate(request);

        Assertions.assertEquals(AwxCredentialType.MACHINE, awxCredential.getType());
    }

    @Test
    public void whenCreateRequestHasNullTypeThenReturnNull() {

        AwxCredentialCreateRequest request = TestAwxCredentialCreateRequest.builder()
                .type(null)
                .build();
        Assertions.assertThrows(PersistenceException.class, () -> awxCredentialService.handleAwxCredentialCreate(request));
    }
}
