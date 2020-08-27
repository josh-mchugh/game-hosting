package com.example.demo.awx.organization.service;

import com.example.demo.awx.organization.model.AwxOrganization;
import com.example.demo.awx.organization.service.model.AwxOrganizationCreateRequest;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.util.TestAwxOrganizationCreateRequest;
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
public class AwxOrganizationServiceTest {

    @Autowired
    private IAwxOrganizationService awxOrganizationService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenEntityExistsThenExistsAnyReturnsTrue() {

        sampleBuilder.builder().awxOrganization().build();

        boolean exists = awxOrganizationService.existsAny();

        Assertions.assertTrue(exists);
    }

    @Test
    public void whenEntityDoesNotExistThenExistsAnyReturnsFalse() {

        boolean exists = awxOrganizationService.existsAny();

        Assertions.assertFalse(exists);
    }

    @Test
    public void whenEntityIsValueThenReturnId() {

        AwxOrganizationCreateRequest request = TestAwxOrganizationCreateRequest.createDefault();
        AwxOrganization awxOrganization = awxOrganizationService.handleOrganizationCreate(request);

        Assertions.assertNotNull(awxOrganization.getId());
    }

    @Test
    public void whenEntityHasNullOrganizationIdThenThrowException() {

        AwxOrganizationCreateRequest request = TestAwxOrganizationCreateRequest.builder()
                .organizationId(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, ()-> awxOrganizationService.handleOrganizationCreate(request));
    }

    @Test
    public void whenEntityHasOrganizationIdThenReturnOrganizationId() {

        AwxOrganizationCreateRequest request = TestAwxOrganizationCreateRequest.createDefault();
        AwxOrganization awxOrganization = awxOrganizationService.handleOrganizationCreate(request);

        Assertions.assertEquals(1L, awxOrganization.getOrganizationId());
    }

    @Test
    public void whenEntityHasNullNameThenThrowException() {

        AwxOrganizationCreateRequest request = TestAwxOrganizationCreateRequest.builder()
                .name(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxOrganizationService.handleOrganizationCreate(request));
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        AwxOrganizationCreateRequest request = TestAwxOrganizationCreateRequest.createDefault();
        AwxOrganization awxOrganization = awxOrganizationService.handleOrganizationCreate(request);

        Assertions.assertEquals("Game Hosting Service", awxOrganization.getName());
    }

    @Test
    public void whenEntityHasNullDescriptionThenReturnNull() {

        AwxOrganizationCreateRequest request = TestAwxOrganizationCreateRequest.builder()
                .description(null)
                .build();
        AwxOrganization awxOrganization = awxOrganizationService.handleOrganizationCreate(request);

        Assertions.assertNull(awxOrganization.getDescription());
    }

    @Test
    public void whenEntityHasDescriptionThenReturnDescription() {

        AwxOrganizationCreateRequest request = TestAwxOrganizationCreateRequest.createDefault();
        AwxOrganization awxOrganization = awxOrganizationService.handleOrganizationCreate(request);

        Assertions.assertEquals("organization description", awxOrganization.getDescription());
    }
}
