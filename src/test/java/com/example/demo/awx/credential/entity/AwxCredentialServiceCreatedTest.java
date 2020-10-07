package com.example.demo.awx.credential.entity;

import com.example.demo.awx.credential.aggregate.event.AwxCredentialCreatedEvent;
import com.example.demo.awx.credential.entity.model.AwxCredential;
import com.example.demo.awx.credential.entity.service.IAwxCredentialService;
import com.example.demo.awx.organization.model.AwxOrganization;
import com.example.demo.sample.SampleBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxCredentialServiceCreatedTest {

    @Autowired
    private IAwxCredentialService awxCredentialService;

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

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .credentialId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        AwxCredential awxCredential = awxCredentialService.handleCreated(event);

        Assertions.assertNotNull(awxCredential);
    }

    @Test
    public void whenCreateRequestIsValidThenReturnId() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .credentialId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        AwxCredential awxCredential = awxCredentialService.handleCreated(event);

        Assertions.assertEquals(awxCredential.getId(), awxCredential.getId());
    }

    @Test
    public void whenCreateRequestHasCredentialIdThenReturnCredentialId() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .credentialId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        AwxCredential awxCredential = awxCredentialService.handleCreated(event);

        Assertions.assertEquals(1L, awxCredential.getCredentialId());
    }

    @Test
    public void whenCreateRequestHasNullCredentialIdThenThrowException() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .credentialId(null)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxCredentialService.handleCreated(event));
    }

    @Test
    public void whenCreateRequestHasOrganizationIdThenReturnNonNull() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .credentialId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        AwxCredential awxCredential = awxCredentialService.handleCreated(event);

        Assertions.assertNotNull(awxCredential);
    }

    @Test
    public void whenCreateRequestHasNullOrganizationIdThenThrowException() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(null)
                .credentialId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxCredentialService.handleCreated(event));
    }

    @Test
    public void whenCreateRequestHasNameThenReturnName() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .credentialId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        AwxCredential awxCredential = awxCredentialService.handleCreated(event);

        Assertions.assertEquals("name", awxCredential.getName());
    }

    @Test
    public void whenCreateRequestHasNullNameThenThrowException() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .credentialId(1L)
                .name(null)
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxCredentialService.handleCreated(event));
    }

    @Test
    public void whenCreateRequestHasDescriptionThenReturnDescription() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .credentialId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        AwxCredential awxCredential = awxCredentialService.handleCreated(event);

        Assertions.assertEquals("description", awxCredential.getDescription());
    }

    @Test
    public void whenCreateRequestHasNullDescriptionThenReturnNull() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .credentialId(1L)
                .name("name")
                .description(null)
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        AwxCredential awxCredential = awxCredentialService.handleCreated(event);

        Assertions.assertNull(awxCredential.getDescription());
    }

    @Test
    public void whenCreateRequestHasPrivateKeyThenReturnPrivateKey() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .credentialId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        AwxCredential awxCredential = awxCredentialService.handleCreated(event);

        Assertions.assertEquals("private key", awxCredential.getPrivateKey());
    }

    @Test
    public void whenCreateRequestHasNullPrivateKeyThenThrowException() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .credentialId(1L)
                .name("name")
                .description("description")
                .privateKey(null)
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxCredentialService.handleCreated(event));
    }

    @Test
    public void whenCreateRequestHasPassphraseThenReturnPassphrase() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .credentialId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        AwxCredential awxCredential = awxCredentialService.handleCreated(event);

        Assertions.assertEquals("passphrase", awxCredential.getPassphrase());
    }

    @Test
    public void whenCreateRequestHasNullPassphraseThenReturnNull() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .credentialId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase(null)
                .type(AwxCredentialType.MACHINE)
                .build();

        AwxCredential awxCredential = awxCredentialService.handleCreated(event);

        Assertions.assertNull(awxCredential.getPassphrase());
    }
    
    @Test
    public void whenCreateRequestHasTypeThenReturnType() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .credentialId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        AwxCredential awxCredential = awxCredentialService.handleCreated(event);

        Assertions.assertEquals(AwxCredentialType.MACHINE, awxCredential.getType());
    }

    @Test
    public void whenCreateRequestHasNullTypeThenReturnNull() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .credentialId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxCredentialService.handleCreated(event));
    }
}
