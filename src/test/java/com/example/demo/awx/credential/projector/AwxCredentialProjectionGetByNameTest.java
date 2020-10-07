package com.example.demo.awx.credential.projector;

import com.example.demo.awx.credential.aggregate.event.AwxCredentialCreatedEvent;
import com.example.demo.awx.credential.entity.AwxCredentialType;
import com.example.demo.awx.credential.entity.model.AwxCredential;
import com.example.demo.awx.credential.entity.service.IAwxCredentialService;
import com.example.demo.awx.credential.projection.IAwxCredentialProjector;
import com.example.demo.awx.organization.model.AwxOrganization;
import com.example.demo.sample.SampleBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxCredentialProjectionGetByNameTest {

    @Autowired
    private IAwxCredentialProjector awxCredentialProjector;

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
    public void whenEntityExistsThenGetByNameReturnNotNull() {

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

        awxCredentialService.handleCreated(event);

        AwxCredential awxCredential = awxCredentialProjector.getByName("name");

        Assertions.assertNotNull(awxCredential);
    }

    @Test
    public void whenEntityDoesNotExistThenGetByNameReturnNull() {

        AwxCredential awxCredential = awxCredentialProjector.getByName("name");

        Assertions.assertNull(awxCredential);
    }

    @Test
    public void whenRequestParamIsNullThenThrowException() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxCredentialProjector.getByName(null));
    }
}
