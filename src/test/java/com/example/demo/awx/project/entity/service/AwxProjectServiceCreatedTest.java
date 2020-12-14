package com.example.demo.awx.project.entity.service;

import com.example.demo.awx.project.aggregate.event.AwxProjectCreatedEvent;
import com.example.demo.awx.project.entity.model.AwxProject;
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
public class AwxProjectServiceCreatedTest {

    @Autowired
    private IAwxProjectService awxProjectService;

    @Autowired
    private SampleBuilder sampleBuilder;

    private SampleData data;

    @BeforeEach
    public void setup() {

        data = sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .build();
    }

    @Test
    public void whenCreateRequestIsNullThenThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> awxProjectService.handleCreated(null));
    }

    @Test
    public void whenCreateRequestIsValidThenReturnNotNull() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(data.getAwxOrganization().getId())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        AwxProject awxProject = awxProjectService.handleCreated(event);

        Assertions.assertNotNull(awxProject);
    }

    @Test
    public void whenCreateRequestIsValidThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(id)
                .awxOrganizationId(data.getAwxOrganization().getId())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        AwxProject awxProject = awxProjectService.handleCreated(event);

        Assertions.assertEquals(id.toString(), awxProject.getId());
    }

    @Test
    public void whenCreateRequestHasAwxOrganizationIdThenReturnNotNull() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(data.getAwxOrganization().getId())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        AwxProject awxProject = awxProjectService.handleCreated(event);

        Assertions.assertNotNull(awxProject);
    }

    @Test
    public void whenCreateRequestHasNullAwxOrganizationIdThenThrowException() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(null)
                .awxCredentialId(data.getAwxCredential().getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxProjectService.handleCreated(event));
    }

    @Test
    public void whenCreateRequestHasCredentialIdThenReturnNotNull() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(data.getAwxOrganization().getId())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        AwxProject awxProject = awxProjectService.handleCreated(event);

        Assertions.assertNotNull(awxProject);
    }

    @Test
    public void whenCreateRequestHasNullCredentialThenThrowException() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(data.getAwxOrganization().getId())
                .awxCredentialId(null)
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxProjectService.handleCreated(event));
    }

    @Test
    public void whenCreateRequestHasAwxIdThenReturnAwxId() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(data.getAwxOrganization().getId())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        AwxProject awxProject = awxProjectService.handleCreated(event);

        Assertions.assertEquals(1L, awxProject.getAwxId());
    }

    @Test
    public void whenCreateRequestHasNullAwxIdThenThrowException() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(data.getAwxOrganization().getId())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxId(null)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxProjectService.handleCreated(event));
    }

    @Test
    public void whenCreateRequestHasNameThenReturnName() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(data.getAwxOrganization().getId())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        AwxProject awxProject = awxProjectService.handleCreated(event);

        Assertions.assertEquals("name", awxProject.getName());
    }

    @Test
    public void whenCreateRequestHasNullNameThenThrowException() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(data.getAwxOrganization().getId())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxId(1L)
                .name(null)
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxProjectService.handleCreated(event));
    }

    @Test
    public void whenCreateRequestHasDescriptionThenReturnDescription() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(data.getAwxOrganization().getId())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        AwxProject awxProject = awxProjectService.handleCreated(event);

        Assertions.assertEquals("description", awxProject.getDescription());
    }

    @Test
    public void whenCreateRequestHasNullDescriptionThenReturnNull() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(data.getAwxOrganization().getId())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxId(1L)
                .name("name")
                .description(null)
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        AwxProject awxProject = awxProjectService.handleCreated(event);

        Assertions.assertNull(awxProject.getDescription());
    }

    @Test
    public void whenCreateRequestHasScmTypeThenReturnScmType() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(data.getAwxOrganization().getId())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        AwxProject awxProject = awxProjectService.handleCreated(event);

        Assertions.assertEquals("type", awxProject.getScmType());
    }

    @Test
    public void whenCreateRequestHasNullScmTypeThenThrowException() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(data.getAwxOrganization().getId())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType(null)
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxProjectService.handleCreated(event));
    }

    @Test
    public void whenCreateRequestHasScmUrlThenReturnScmUrl() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(data.getAwxOrganization().getId())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        AwxProject awxProject = awxProjectService.handleCreated(event);

        Assertions.assertEquals("url", awxProject.getScmUrl());
    }

    @Test
    public void whenCreateRequestHasNullScmUrlThenThrowException() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(data.getAwxOrganization().getId())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxProjectService.handleCreated(event));
    }

    @Test
    public void whenCreateRequestHasScmBranchThenReturnScmBranch() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(data.getAwxOrganization().getId())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        AwxProject awxProject = awxProjectService.handleCreated(event);

        Assertions.assertEquals("branch", awxProject.getScmBranch());
    }

    @Test
    public void whenCreateRequestHasNullScmBranchThenThrowException() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(data.getAwxOrganization().getId())
                .awxCredentialId(data.getAwxCredential().getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch(null)
                .scmUrl("url")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxProjectService.handleCreated(event));
    }
}
