package com.example.demo.awx.credential.service;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import com.example.demo.awx.credential.entity.model.AwxCredential;
import com.example.demo.awx.credential.service.model.AwxCredentialCreateRequest;
import com.example.demo.awx.organization.entity.model.AwxOrganization;
import com.example.demo.sample.SampleBuilder;
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
public class AwxCredentialServiceCreatedTest {

    @Autowired
    private AwxCredentialService awxCredentialService;

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

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        AwxCredential awxCredential = awxCredentialService.handleCreated(request);

        Assertions.assertNotNull(awxCredential);
    }

    @Test
    public void whenCreateRequestIsValidThenReturnId() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        AwxCredential awxCredential = awxCredentialService.handleCreated(request);

        Assertions.assertEquals(awxCredential.getId(), awxCredential.getId());
    }

    @Test
    public void whenCreateRequestHasAwxIdThenReturnAwxId() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        AwxCredential awxCredential = awxCredentialService.handleCreated(request);

        Assertions.assertEquals(1L, awxCredential.getAwxId());
    }

    @Test
    public void whenCreateRequestHasNullAwxIdThenThrowException() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(null)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxCredentialService.handleCreated(request));
    }

    @Test
    public void whenCreateRequestHasOrganizationIdThenReturnNonNull() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        AwxCredential awxCredential = awxCredentialService.handleCreated(request);

        Assertions.assertNotNull(awxCredential);
    }

    @Test
    public void whenCreateRequestHasNullOrganizationIdThenThrowException() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .awxOrganizationId(null)
                .awxId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxCredentialService.handleCreated(request));
    }

    @Test
    public void whenCreateRequestHasNameThenReturnName() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        AwxCredential awxCredential = awxCredentialService.handleCreated(request);

        Assertions.assertEquals("name", awxCredential.getName());
    }

    @Test
    public void whenCreateRequestHasNullNameThenThrowException() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name(null)
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxCredentialService.handleCreated(request));
    }

    @Test
    public void whenCreateRequestHasDescriptionThenReturnDescription() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        AwxCredential awxCredential = awxCredentialService.handleCreated(request);

        Assertions.assertEquals("description", awxCredential.getDescription());
    }

    @Test
    public void whenCreateRequestHasNullDescriptionThenReturnNull() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description(null)
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        AwxCredential awxCredential = awxCredentialService.handleCreated(request);

        Assertions.assertNull(awxCredential.getDescription());
    }

    @Test
    public void whenCreateRequestHasPrivateKeyThenReturnPrivateKey() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        AwxCredential awxCredential = awxCredentialService.handleCreated(request);

        Assertions.assertEquals("private key", awxCredential.getPrivateKey());
    }

    @Test
    public void whenCreateRequestHasNullPrivateKeyThenThrowException() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .privateKey(null)
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxCredentialService.handleCreated(request));
    }

    @Test
    public void whenCreateRequestHasPassphraseThenReturnPassphrase() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        AwxCredential awxCredential = awxCredentialService.handleCreated(request);

        Assertions.assertEquals("passphrase", awxCredential.getPassphrase());
    }

    @Test
    public void whenCreateRequestHasNullPassphraseThenReturnNull() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase(null)
                .type(AwxCredentialType.MACHINE)
                .build();

        AwxCredential awxCredential = awxCredentialService.handleCreated(request);

        Assertions.assertNull(awxCredential.getPassphrase());
    }
    
    @Test
    public void whenCreateRequestHasTypeThenReturnType() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        AwxCredential awxCredential = awxCredentialService.handleCreated(request);

        Assertions.assertEquals(AwxCredentialType.MACHINE, awxCredential.getType());
    }

    @Test
    public void whenCreateRequestHasNullTypeThenReturnNull() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxCredentialService.handleCreated(request));
    }
}
