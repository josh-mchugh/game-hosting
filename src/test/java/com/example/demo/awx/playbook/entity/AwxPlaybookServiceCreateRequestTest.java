package com.example.demo.awx.playbook.entity;

import com.example.demo.awx.playbook.aggregate.event.AwxPlaybookCreatedEvent;
import com.example.demo.awx.playbook.entity.model.AwxPlaybook;
import com.example.demo.awx.playbook.entity.service.IAwxPlaybookService;
import com.example.demo.awx.project.entity.model.AwxProject;
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
public class AwxPlaybookServiceCreateRequestTest {

    @Autowired
    private IAwxPlaybookService awxPlaybookService;

    @Autowired
    private SampleBuilder sampleBuilder;

    private AwxProject awxProject;

    @BeforeEach
    public void setup() {

        awxProject = sampleBuilder.builder()
                .awxOrganization()
                .awxGitlabCredential()
                .awxProject()
                .build()
                .getAwxProject();
    }

    @Test
    public void whenCreateRequestIsNullThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> awxPlaybookService.handleAwxPlaybookCreated(null));
    }

    @Test
    public void whenCreateRequestIsValidThenReturnNotNull() {

        AwxPlaybookCreatedEvent event = AwxPlaybookCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxProjectId(awxProject.getId())
                .name("cowsay_playbook.yml")
                .type(PlaybookType.COWSAY)
                .build();
        AwxPlaybook awxPlaybook = awxPlaybookService.handleAwxPlaybookCreated(event);

        Assertions.assertNotNull(awxPlaybook);
    }

    @Test
    public void whenCreateRequestIsValidThenReturnId() {

        AwxPlaybookCreatedEvent event = AwxPlaybookCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxProjectId(awxProject.getId())
                .name("cowsay_playbook.yml")
                .type(PlaybookType.COWSAY)
                .build();
        AwxPlaybook awxPlaybook = awxPlaybookService.handleAwxPlaybookCreated(event);

        Assertions.assertNotNull(awxPlaybook.getId());
    }

    @Test
    public void whenCreateRequestHasNullProjectIdThenThrowException() {

        AwxPlaybookCreatedEvent event = AwxPlaybookCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxProjectId(null)
                .name("cowsay-playbook.yml")
                .type(PlaybookType.COWSAY)
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxPlaybookService.handleAwxPlaybookCreated(event));
    }

    @Test
    public void whenCreateRequestHasInvalidProjectIdThenThrowException() {

        AwxPlaybookCreatedEvent event = AwxPlaybookCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxProjectId("asdfasdf")
                .name("cowsay-playbook.yml")
                .type(PlaybookType.COWSAY)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxPlaybookService.handleAwxPlaybookCreated(event));
    }

    @Test
    public void whenCreateRequestHasValidNameThenReturnName() {

        AwxPlaybookCreatedEvent event = AwxPlaybookCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxProjectId(awxProject.getId())
                .name("cowsay-playbook.yml")
                .type(PlaybookType.COWSAY)
                .build();
        AwxPlaybook awxPlaybook = awxPlaybookService.handleAwxPlaybookCreated(event);

        Assertions.assertEquals("cowsay-playbook.yml", awxPlaybook.getName());
    }

    @Test
    public void whenCreateRequestHasNullNameThenThrowException() {

        AwxPlaybookCreatedEvent event = AwxPlaybookCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxProjectId(awxProject.getId())
                .name(null)
                .type(PlaybookType.COWSAY)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxPlaybookService.handleAwxPlaybookCreated(event));
    }

    @Test
    public void whenCreateRequestHasValidThenReturnType() {

        AwxPlaybookCreatedEvent event = AwxPlaybookCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxProjectId(awxProject.getId())
                .name("cowsay-playbook.yml")
                .type(PlaybookType.COWSAY)
                .build();
        AwxPlaybook awxPlaybook = awxPlaybookService.handleAwxPlaybookCreated(event);

        Assertions.assertEquals(PlaybookType.COWSAY, awxPlaybook.getType());
    }
}
