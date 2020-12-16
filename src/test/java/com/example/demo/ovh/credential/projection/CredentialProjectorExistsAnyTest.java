package com.example.demo.ovh.credential.projection;

import com.example.demo.ovh.credential.aggregate.event.CredentialCreatedEvent;
import com.example.demo.ovh.credential.entity.CredentialType;
import com.example.demo.ovh.credential.entity.service.ICredentialService;
import com.example.demo.ovh.credential.projector.ICredentialProjector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class CredentialProjectorExistsAnyTest {

    @Autowired
    private ICredentialService credentialService;

    @Autowired
    private ICredentialProjector credentialProjector;

    @Test
    public void whenEntitiesDoNotExistThenExistsAllReturnsFalse() {

        boolean exists = credentialProjector.existsAny();

        Assertions.assertFalse(exists);
    }

    @Test
    public void whenEntitiesDoExistThenExistsAllReturnTrue() {

        CredentialCreatedEvent event = CredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .ovhId("ovhId")
                .name("credential name")
                .publicKey("public key")
                .type(CredentialType.ANSIBLE)
                .build();
        credentialService.handleCreated(event);

        boolean exists = credentialProjector.existsAny();

        Assertions.assertTrue(exists);
    }
}
