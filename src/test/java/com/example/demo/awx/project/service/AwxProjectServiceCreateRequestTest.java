package com.example.demo.awx.project.service;

import com.example.demo.awx.credential.entity.model.AwxCredential;
import com.example.demo.awx.organization.model.AwxOrganization;
import com.example.demo.awx.project.model.AwxProject;
import com.example.demo.awx.project.service.model.AwxProjectCreateRequest;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.util.TestAwxProjectCreateRequest;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxProjectServiceCreateRequestTest {

    @Autowired
    private IAwxProjectService awxProjectService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void whenCreateRequestIsNullThenThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> awxProjectService.handleCreateRequest(null));
    }

    @Test
    public void whenCreateRequestIsValidThenReturnNotNull() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .build();

        AwxProjectCreateRequest request = TestAwxProjectCreateRequest.createDefault();
        AwxProject awxProject = awxProjectService.handleCreateRequest(request);

        Assertions.assertNotNull(awxProject);
    }

    @Test
    public void whenCreateRequestIsValidThenReturnId() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .build();

        AwxProjectCreateRequest request = TestAwxProjectCreateRequest.createDefault();
        AwxProject awxProject = awxProjectService.handleCreateRequest(request);

        Assertions.assertNotNull(awxProject.getId());
    }

    @Test
    public void whenCreateRequestHasOrganizationIdThenReturnNotNull() {

        AwxOrganization awxOrganization = sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .build()
                .getAwxOrganization();

        AwxProjectCreateRequest request = TestAwxProjectCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getOrganizationId())
                .build();
        AwxProject awxProject = awxProjectService.handleCreateRequest(request);

        Assertions.assertNotNull(awxProject);
    }

    @Test
    public void whenCreateRequestHasNullOrganizationIdThenThrowException() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .build();

        AwxProjectCreateRequest request = TestAwxProjectCreateRequest.builder()
                .awxOrganizationId(null)
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxProjectService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasCredentialIdThenReturnNotNull() {

        AwxCredential awxCredential = sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .build()
                .getAwxCredential();

        AwxProjectCreateRequest request = TestAwxProjectCreateRequest.builder()
                .awxCredentialId(awxCredential.getCredentialId())
                .build();
        AwxProject awxProject = awxProjectService.handleCreateRequest(request);

        Assertions.assertNotNull(awxProject);
    }

    @Test
    public void whenCreateRequestHasNullCredentialThenThrowException() {

        sampleBuilder.builder()
                .awxOrganization()
                .build();

        AwxProjectCreateRequest request = TestAwxProjectCreateRequest.builder()
                .awxCredentialId(null)
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxProjectService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasProjectIdThenReturnProjectId() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .build();

        AwxProjectCreateRequest request = TestAwxProjectCreateRequest.builder()
                .projectId(1L)
                .build();
        AwxProject awxProject = awxProjectService.handleCreateRequest(request);

        Assertions.assertEquals(1L, awxProject.getProjectId());
    }

    @Test
    public void whenCreateRequestHasNullProjectIdThenThrowException() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .build();

        AwxProjectCreateRequest request = TestAwxProjectCreateRequest.builder()
                .projectId(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxProjectService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasNameThenReturnName() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .build();

        AwxProjectCreateRequest request = TestAwxProjectCreateRequest.builder()
                .name("name")
                .build();
        AwxProject awxProject = awxProjectService.handleCreateRequest(request);

        Assertions.assertEquals("name", awxProject.getName());
    }

    @Test
    public void whenCreateRequestHasNullNameThenThrowException() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .build();

        AwxProjectCreateRequest request = TestAwxProjectCreateRequest.builder()
                .name(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxProjectService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasDescriptionThenReturnDescription() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .build();

        AwxProjectCreateRequest request = TestAwxProjectCreateRequest.builder()
                .description("description")
                .build();
        AwxProject awxProject = awxProjectService.handleCreateRequest(request);

        Assertions.assertEquals("description", awxProject.getDescription());
    }

    @Test
    public void whenCreateRequestHasNullDescriptionThenReturnNull() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .build();

        AwxProjectCreateRequest request = TestAwxProjectCreateRequest.builder()
                .description(null)
                .build();
        AwxProject awxProject = awxProjectService.handleCreateRequest(request);

        Assertions.assertNull(awxProject.getDescription());
    }

    @Test
    public void whenCreateRequestHasScmTypeThenReturnScmType() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .build();

        AwxProjectCreateRequest request = TestAwxProjectCreateRequest.builder()
                .scmType("scm type")
                .build();
        AwxProject awxProject = awxProjectService.handleCreateRequest(request);

        Assertions.assertEquals("scm type", stringEncryptor.decrypt(awxProject.getScmType()));
    }

    @Test
    public void whenCreateRequestHasNullScmTypeThenThrowException() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .build();

        AwxProjectCreateRequest request = TestAwxProjectCreateRequest.builder()
                .scmType(null)
                .build();
        Assertions.assertThrows(PersistenceException.class, () -> awxProjectService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasScmUrlThenReturnScmUrl() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .build();

        AwxProjectCreateRequest request = TestAwxProjectCreateRequest.builder()
                .scmUrl("scm url")
                .build();
        AwxProject awxProject = awxProjectService.handleCreateRequest(request);

        Assertions.assertEquals("scm url", stringEncryptor.decrypt(awxProject.getScmUrl()));
    }

    @Test
    public void whenCreateRequestHasNullScmUrlThenThrowException() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .build();

        AwxProjectCreateRequest request = TestAwxProjectCreateRequest.builder()
                .scmType(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxProjectService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasScmBranchThenReturnScmBranch() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .build();

        AwxProjectCreateRequest request = TestAwxProjectCreateRequest.builder()
                .scmBranch("scm branch")
                .build();
        AwxProject awxProject = awxProjectService.handleCreateRequest(request);

        Assertions.assertEquals("scm branch", stringEncryptor.decrypt(awxProject.getScmBranch()));
    }

    @Test
    public void whenCreateRequestHasNullScmBranchThenThrowException() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .build();

        AwxProjectCreateRequest request = TestAwxProjectCreateRequest.builder()
                .scmBranch(null)
                .build();
        Assertions.assertThrows(PersistenceException.class, () -> awxProjectService.handleCreateRequest(request));
    }
}
