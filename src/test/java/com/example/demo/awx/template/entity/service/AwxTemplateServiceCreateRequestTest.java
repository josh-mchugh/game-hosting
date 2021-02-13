package com.example.demo.awx.template.entity.service;

import com.example.demo.awx.template.aggregate.event.AwxTemplateCreatedEvent;
import com.example.demo.awx.template.entity.TemplateJobType;
import com.example.demo.awx.template.entity.TemplateVerbosity;
import com.example.demo.awx.template.entity.model.AwxTemplate;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
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
public class AwxTemplateServiceCreateRequestTest {

    @Autowired
    private IAwxTemplateService awxTemplateService;

    @Autowired
    private SampleBuilder sampleBuilder;

    private SampleData data;

    @BeforeEach
    public void setup() {

        data = sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .awxInventory()
                .awxProject()
                .awxPlaybook()
                .build();
    }

    @Test
    public void whenCreateRequestParamIsNullThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> awxTemplateService.handleAwxTemplateCreated(null));
    }

    @Test
    public void whenCreateRequestIsValidThenReturnNotNull() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxInventoryId(data.getAwxInventory().getId())
                .awxPlaybookId(data.getAwxPlaybook().getId())
                .awxId(1L)
                .name("run job")
                .description("runs a job")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        AwxTemplate awxTemplate = awxTemplateService.handleAwxTemplateCreated(event);

        Assertions.assertNotNull(awxTemplate);
    }

    @Test
    public void whenCreateRequestIsValidThenReturnId() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxInventoryId(data.getAwxInventory().getId())
                .awxPlaybookId(data.getAwxPlaybook().getId())
                .awxId(1L)
                .name("run job")
                .description("runs a job")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        AwxTemplate awxTemplate = awxTemplateService.handleAwxTemplateCreated(event);

        Assertions.assertEquals(event.getId().toString(), awxTemplate.getId());
    }

    @Test
    public void whenCreateRequestHasNullCredentialIdThenThrowException() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxCredentialId(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> awxTemplateService.handleAwxTemplateCreated(event));
    }

    @Test
    public void whenCreateRequestHasNullInventoryIdThenThrowException() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxInventoryId(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> awxTemplateService.handleAwxTemplateCreated(event));
    }

    @Test
    public void whenCreateRequestHasNullPlaybookTypeThenThrowException() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxInventoryId(data.getAwxInventory().getId())
                .awxPlaybookId(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> awxTemplateService.handleAwxTemplateCreated(event));
    }

    @Test
    public void whenCreateRequestHasAwxIdThenReturnAwxId() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxInventoryId(data.getAwxInventory().getId())
                .awxPlaybookId(data.getAwxPlaybook().getId())
                .awxId(1L)
                .name("run job")
                .description("runs a job")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        AwxTemplate awxTemplate = awxTemplateService.handleAwxTemplateCreated(event);

        Assertions.assertEquals(1L, awxTemplate.getAwxId());
    }

    @Test
    public void whenCreateRequestHasNullAwxIdThenThrowException() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxInventoryId(data.getAwxInventory().getId())
                .awxPlaybookId(data.getAwxPlaybook().getId())
                .awxId(null)
                .name("run job")
                .description("runs a job")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxTemplateService.handleAwxTemplateCreated(event));
    }

    @Test
    public void whenCreateRequestHasNameThenReturnName() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxInventoryId(data.getAwxInventory().getId())
                .awxPlaybookId(data.getAwxPlaybook().getId())
                .awxId(1L)
                .name("run job")
                .description("runs a job")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        AwxTemplate awxTemplate = awxTemplateService.handleAwxTemplateCreated(event);

        Assertions.assertEquals("run job", awxTemplate.getName());
    }

    @Test
    public void whenCreateRequestHasNullNameThenThrowException() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxInventoryId(data.getAwxInventory().getId())
                .awxPlaybookId(data.getAwxPlaybook().getId())
                .awxId(1L)
                .name(null)
                .description("runs a job")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxTemplateService.handleAwxTemplateCreated(event));
    }

    @Test
    public void whenCreateRequestHasDescriptionThenReturnDescription() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxInventoryId(data.getAwxInventory().getId())
                .awxPlaybookId(data.getAwxPlaybook().getId())
                .awxId(1L)
                .name("run job")
                .description("runs a job")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        AwxTemplate awxTemplate = awxTemplateService.handleAwxTemplateCreated(event);

        Assertions.assertEquals("runs a job", awxTemplate.getDescription());
    }

    @Test
    public void whenCreateRequestHasNullDescriptionThenReturnNull() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxInventoryId(data.getAwxInventory().getId())
                .awxPlaybookId(data.getAwxPlaybook().getId())
                .awxId(1L)
                .name("run job")
                .description(null)
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        AwxTemplate awxTemplate = awxTemplateService.handleAwxTemplateCreated(event);

        Assertions.assertNull(awxTemplate.getDescription());
    }

    @Test
    public void whenCreateRequestHasJobTypeThenReturnJobType() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxInventoryId(data.getAwxInventory().getId())
                .awxPlaybookId(data.getAwxPlaybook().getId())
                .awxId(1L)
                .name("run job")
                .description("runs a job")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        AwxTemplate awxTemplate = awxTemplateService.handleAwxTemplateCreated(event);

        Assertions.assertEquals(TemplateJobType.RUN, awxTemplate.getType());
    }

    @Test
    public void whenCreateRequestHasNullTypeThenThrowException() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxInventoryId(data.getAwxInventory().getId())
                .awxPlaybookId(data.getAwxPlaybook().getId())
                .awxId(1L)
                .name("run job")
                .description("runs a job")
                .type(null)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxTemplateService.handleAwxTemplateCreated(event));
    }

    @Test
    public void whenCreateRequestHasVerbosityThenReturnVerbosity() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxInventoryId(data.getAwxInventory().getId())
                .awxPlaybookId(data.getAwxPlaybook().getId())
                .awxId(1L)
                .name("run job")
                .description("runs a job")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        AwxTemplate awxTemplate = awxTemplateService.handleAwxTemplateCreated(event);

        Assertions.assertEquals(TemplateVerbosity.NORMAL, awxTemplate.getVerbosity());
    }

    @Test
    public void whenCreateRequestHasNullVerbosityThenThrowException() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxInventoryId(data.getAwxInventory().getId())
                .awxPlaybookId(data.getAwxPlaybook().getId())
                .awxId(1L)
                .name("run job")
                .description("runs a job")
                .type(TemplateJobType.RUN)
                .verbosity(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxTemplateService.handleAwxTemplateCreated(event));
    }
}
