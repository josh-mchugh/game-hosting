package com.example.demo.awx.playbook.service;

import com.example.demo.awx.playbook.entity.PlaybookType;
import com.example.demo.awx.playbook.model.AwxPlaybook;
import com.example.demo.awx.playbook.service.model.AwxPlaybookCreateRequest;
import com.example.demo.awx.project.model.AwxProject;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.util.TestAwxPlaybookCreateRequest;
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

        Assertions.assertThrows(NullPointerException.class, () -> awxPlaybookService.handleCreateRequest(null));
    }

    @Test
    public void whenCreateRequestIsValidThenReturnNotNull() {

        AwxPlaybookCreateRequest request = TestAwxPlaybookCreateRequest.builder()
                .projectId(awxProject.getProjectId())
                .build();
        AwxPlaybook awxPlaybook = awxPlaybookService.handleCreateRequest(request);

        Assertions.assertNotNull(awxPlaybook);
    }

    @Test
    public void whenCreateRequestIsValidThenReturnId() {

        AwxPlaybookCreateRequest request = TestAwxPlaybookCreateRequest.builder()
                .projectId(awxProject.getProjectId())
                .build();
        AwxPlaybook awxPlaybook = awxPlaybookService.handleCreateRequest(request);

        Assertions.assertNotNull(awxPlaybook.getId());
    }

    @Test
    public void whenCreateRequestHasNullProjectIdThenThrowException() {

        AwxPlaybookCreateRequest request = TestAwxPlaybookCreateRequest.builder()
                .projectId(null)
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxPlaybookService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasInvalidProjectIdThenThrowException() {

        AwxPlaybookCreateRequest request = TestAwxPlaybookCreateRequest.builder()
                .projectId(999L)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxPlaybookService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasValidNameThenReturnName() {

        AwxPlaybookCreateRequest request = TestAwxPlaybookCreateRequest.builder()
                .name("cowsay-playbook.yml")
                .build();
        AwxPlaybook awxPlaybook = awxPlaybookService.handleCreateRequest(request);

        Assertions.assertEquals("cowsay-playbook.yml", awxPlaybook.getName());
    }

    @Test
    public void whenCreateRequestHasInvalidNameThenThrowException() {

        AwxPlaybookCreateRequest request = TestAwxPlaybookCreateRequest.builder()
                .name("invalid name")
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxPlaybookService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasIncorrectFormattedNameThenThrowException() {

        AwxPlaybookCreateRequest request = TestAwxPlaybookCreateRequest.builder()
                .name("cowsay_playbook.yml")
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxPlaybookService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasNullNameThenThrowException() {

        AwxPlaybookCreateRequest request = TestAwxPlaybookCreateRequest.builder()
                .name(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> awxPlaybookService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasValidThenReturnType() {

        AwxPlaybookCreateRequest request = TestAwxPlaybookCreateRequest.builder()
                .name("cowsay-playbook.yml")
                .build();
        AwxPlaybook awxPlaybook = awxPlaybookService.handleCreateRequest(request);

        Assertions.assertEquals(PlaybookType.COWSAY, awxPlaybook.getType());
    }
}
