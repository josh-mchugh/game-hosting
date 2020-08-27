package com.example.demo.awx.template.service;

import com.example.demo.awx.template.entity.TemplateJobType;
import com.example.demo.awx.template.entity.TemplateVerbosity;
import com.example.demo.awx.template.model.AwxTemplate;
import com.example.demo.awx.template.service.model.AwxTemplateCreateRequest;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import com.example.demo.sample.util.TestAwxTemplateCreateRequest;
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

        Assertions.assertThrows(NullPointerException.class, () -> awxTemplateService.handleCreateRequest(null));
    }

    @Test
    public void whenCreateRequestIsValidThenReturnNotNull() {

        AwxTemplateCreateRequest request = TestAwxTemplateCreateRequest.builder()
                .awxCredential(data.getAwxCredential())
                .awxInventory(data.getAwxInventory())
                .awxPlaybook(data.getAwxPlaybook())
                .build();

        AwxTemplate awxTemplate = awxTemplateService.handleCreateRequest(request);

        Assertions.assertNotNull(awxTemplate);
    }

    @Test
    public void whenCreateRequestIsValidThenReturnId() {

        AwxTemplateCreateRequest request = TestAwxTemplateCreateRequest.builder()
                .awxCredential(data.getAwxCredential())
                .awxInventory(data.getAwxInventory())
                .awxPlaybook(data.getAwxPlaybook())
                .build();

        AwxTemplate awxTemplate = awxTemplateService.handleCreateRequest(request);

        Assertions.assertNotNull(awxTemplate.getId());
    }

    @Test
    public void whenCreateRequestHasNullCredentialIdThenThrowException() {

        AwxTemplateCreateRequest request = TestAwxTemplateCreateRequest.builder()
                .credentialId(null)
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxTemplateService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasNullInventoryIdThenThrowException() {

        AwxTemplateCreateRequest request = TestAwxTemplateCreateRequest.builder()
                .awxCredential(data.getAwxCredential())
                .inventoryId(null)
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxTemplateService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasNullPlaybookTypeThenThrowException() {

        AwxTemplateCreateRequest request = TestAwxTemplateCreateRequest.builder()
                .awxCredential(data.getAwxCredential())
                .awxInventory(data.getAwxInventory())
                .playbookType(null)
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxTemplateService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasTemplateIdThenReturnTemplateId() {

        AwxTemplateCreateRequest request = TestAwxTemplateCreateRequest.builder()
                .awxCredential(data.getAwxCredential())
                .awxInventory(data.getAwxInventory())
                .awxPlaybook(data.getAwxPlaybook())
                .templateId(1L)
                .build();

        AwxTemplate awxTemplate = awxTemplateService.handleCreateRequest(request);

        Assertions.assertEquals(1L, awxTemplate.getTemplateId());
    }

    @Test
    public void whenCreateRequestHasNullTemplateIdThenThrowException() {

        AwxTemplateCreateRequest request = TestAwxTemplateCreateRequest.builder()
                .awxCredential(data.getAwxCredential())
                .awxInventory(data.getAwxInventory())
                .awxPlaybook(data.getAwxPlaybook())
                .templateId(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxTemplateService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasNameThenReturnName() {

        AwxTemplateCreateRequest request = TestAwxTemplateCreateRequest.builder()
                .awxCredential(data.getAwxCredential())
                .awxInventory(data.getAwxInventory())
                .awxPlaybook(data.getAwxPlaybook())
                .name("name")
                .build();

        AwxTemplate awxTemplate = awxTemplateService.handleCreateRequest(request);

        Assertions.assertEquals("name", awxTemplate.getName());
    }

    @Test
    public void whenCreateRequestHasNullNameThenThrowException() {

        AwxTemplateCreateRequest request = TestAwxTemplateCreateRequest.builder()
                .awxCredential(data.getAwxCredential())
                .awxInventory(data.getAwxInventory())
                .awxPlaybook(data.getAwxPlaybook())
                .name(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxTemplateService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasDescriptionThenReturnDescription() {

        AwxTemplateCreateRequest request = TestAwxTemplateCreateRequest.builder()
                .awxCredential(data.getAwxCredential())
                .awxInventory(data.getAwxInventory())
                .awxPlaybook(data.getAwxPlaybook())
                .description("description")
                .build();

        AwxTemplate awxTemplate = awxTemplateService.handleCreateRequest(request);

        Assertions.assertEquals("description", awxTemplate.getDescription());
    }

    @Test
    public void whenCreateRequestHasNullDescriptionThenReturnNull() {

        AwxTemplateCreateRequest request = TestAwxTemplateCreateRequest.builder()
                .awxCredential(data.getAwxCredential())
                .awxInventory(data.getAwxInventory())
                .awxPlaybook(data.getAwxPlaybook())
                .description(null)
                .build();

        AwxTemplate awxTemplate = awxTemplateService.handleCreateRequest(request);

        Assertions.assertNull(awxTemplate.getDescription());
    }

    @Test
    public void whenCreateRequestHasJobTypeThenReturnJobType() {

        AwxTemplateCreateRequest request = TestAwxTemplateCreateRequest.builder()
                .awxCredential(data.getAwxCredential())
                .awxInventory(data.getAwxInventory())
                .awxPlaybook(data.getAwxPlaybook())
                .templateJobType(TemplateJobType.RUN)
                .build();

        AwxTemplate awxTemplate = awxTemplateService.handleCreateRequest(request);

        Assertions.assertEquals(TemplateJobType.RUN, awxTemplate.getJobType());
    }

    @Test
    public void whenCreateRequestHasNullJobTypeThenThrowException() {

        AwxTemplateCreateRequest request = TestAwxTemplateCreateRequest.builder()
                .awxCredential(data.getAwxCredential())
                .awxInventory(data.getAwxInventory())
                .awxPlaybook(data.getAwxPlaybook())
                .templateJobType(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxTemplateService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasVerbosityThenReturnVerbosity() {

        AwxTemplateCreateRequest request = TestAwxTemplateCreateRequest.builder()
                .awxCredential(data.getAwxCredential())
                .awxInventory(data.getAwxInventory())
                .awxPlaybook(data.getAwxPlaybook())
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        AwxTemplate awxTemplate = awxTemplateService.handleCreateRequest(request);

        Assertions.assertEquals(TemplateVerbosity.NORMAL, awxTemplate.getVerbosity());
    }

    @Test
    public void whenCreateRequestHasNullVerbosityThenThrowException() {

        AwxTemplateCreateRequest request = TestAwxTemplateCreateRequest.builder()
                .awxCredential(data.getAwxCredential())
                .awxInventory(data.getAwxInventory())
                .awxPlaybook(data.getAwxPlaybook())
                .verbosity(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxTemplateService.handleCreateRequest(request));
    }
}
