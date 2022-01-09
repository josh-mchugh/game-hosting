package com.example.demo.awx.organization.service;

import com.example.demo.awx.organization.service.model.AwxOrganizationCreateRequest;
import com.example.demo.awx.organization.entity.model.AwxOrganization;
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
public class AwxOrganizationServiceCreatedTest {

    @Autowired
    private AwxOrganizationService awxOrganizationService;

    @Test
    public void whenRequestHasIdThenReturnId() {

        AwxOrganizationCreateRequest request = AwxOrganizationCreateRequest.builder()
                .awxId(1L)
                .name("name")
                .description("description")
                .build();

        AwxOrganization awxOrganization = awxOrganizationService.handleCreate(request);

        Assertions.assertNotNull(awxOrganization.getId());
    }

    @Test
    public void whenRequestHasNullAwxIdThenThrowException() {

        AwxOrganizationCreateRequest request = AwxOrganizationCreateRequest.builder()
                .awxId(null)
                .name("name")
                .description("description")
                .build();

        Assertions.assertThrows(PersistenceException.class, ()-> awxOrganizationService.handleCreate(request));
    }

    @Test
    public void whenRequestHasAwxIdThenReturnAwxId() {

        AwxOrganizationCreateRequest request = AwxOrganizationCreateRequest.builder()
                .awxId(1L)
                .name("name")
                .description("description")
                .build();

        AwxOrganization awxOrganization = awxOrganizationService.handleCreate(request);

        Assertions.assertEquals(1L, awxOrganization.getAwxId());
    }

    @Test
    public void whenRequestHasNullNameThenThrowException() {

        AwxOrganizationCreateRequest request = AwxOrganizationCreateRequest.builder()
                .awxId(1L)
                .name(null)
                .description("description")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxOrganizationService.handleCreate(request));
    }

    @Test
    public void whenRequestHasNameThenReturnName() {

        AwxOrganizationCreateRequest request = AwxOrganizationCreateRequest.builder()
                .awxId(1L)
                .name("name")
                .description("description")
                .build();

        AwxOrganization awxOrganization = awxOrganizationService.handleCreate(request);

        Assertions.assertEquals("name", awxOrganization.getName());
    }

    @Test
    public void whenRequestHasNullDescriptionThenReturnNull() {

        AwxOrganizationCreateRequest request = AwxOrganizationCreateRequest.builder()
                .awxId(1L)
                .name("name")
                .description(null)
                .build();

        AwxOrganization awxOrganization = awxOrganizationService.handleCreate(request);

        Assertions.assertNull(awxOrganization.getDescription());
    }

    @Test
    public void whenRequestHasDescriptionThenReturnDescription() {

        AwxOrganizationCreateRequest request = AwxOrganizationCreateRequest.builder()
                .awxId(1L)
                .name("name")
                .description("description")
                .build();

        AwxOrganization awxOrganization = awxOrganizationService.handleCreate(request);

        Assertions.assertEquals("description", awxOrganization.getDescription());
    }
}
