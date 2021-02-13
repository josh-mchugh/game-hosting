package com.example.demo.ovh.credential.entity.service;

import com.example.demo.ovh.credential.aggregate.event.CredentialCreatedEvent;
import com.example.demo.ovh.credential.entity.CredentialType;
import com.example.demo.ovh.credential.entity.model.Credential;
import org.junit.jupiter.api.Assertions;
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
public class CredentialServiceCreatedTest {

    @Autowired
    private ICredentialService credentialService;

    @Test
    public void whenCreatedIsValidThenReturnNotNull() {

        CredentialCreatedEvent event = CredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .ovhId("ovhId")
                .name("name")
                .publicKey("public-key")
                .type(CredentialType.ANSIBLE)
                .build();

        Credential credential = credentialService.handleCreated(event);

        Assertions.assertNotNull(credential);
    }

    @Test
    public void whenCreatedHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> credentialService.handleCreated(null));
    }

    @Test
    public void whenCreatedHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        CredentialCreatedEvent event = CredentialCreatedEvent.builder()
                .id(id)
                .ovhId("ovhId")
                .build();

        Credential credential = credentialService.handleCreated(event);

        Assertions.assertEquals(id, credential.getId());
    }

    @Test
    public void whenCreatedHasNullIdThenThrowException() {

        CredentialCreatedEvent event = CredentialCreatedEvent.builder()
                .id(null)
                .ovhId("ovhId")
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> credentialService.handleCreated(event));
    }

    @Test
    public void whenCreatedHasOvhIdThenReturnOvhId() {

        CredentialCreatedEvent event = CredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .ovhId("ovhId")
                .build();

        Credential credential = credentialService.handleCreated(event);

        Assertions.assertEquals("ovhId", credential.getSshKeyId());
    }

    @Test
    public void whenCreatedHasNullOvhIdThenThrowException() {

        CredentialCreatedEvent event = CredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .ovhId(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> credentialService.handleCreated(event));
    }

    @Test
    public void whenCreatedHasNameThenReturnName() {

        CredentialCreatedEvent event = CredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .ovhId("ovhId")
                .name("name")
                .build();

        Credential credential = credentialService.handleCreated(event);

        Assertions.assertEquals("name", credential.getName());
    }

    @Test
    public void whenCreatedHasPublicKeyThenReturnPublicKey() {

        CredentialCreatedEvent event = CredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .ovhId("ovhId")
                .name("name")
                .publicKey("public-key")
                .build();

        Credential credential = credentialService.handleCreated(event);

        Assertions.assertEquals("public-key", credential.getPublicKey());
    }

    @Test
    public void whenCreatedHasTypeThenReturnType() {

        CredentialCreatedEvent event = CredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .ovhId("ovhId")
                .name("name")
                .publicKey("public-key")
                .type(CredentialType.ANSIBLE)
                .build();

        Credential credential = credentialService.handleCreated(event);

        Assertions.assertEquals(CredentialType.ANSIBLE, credential.getType());
    }
}
