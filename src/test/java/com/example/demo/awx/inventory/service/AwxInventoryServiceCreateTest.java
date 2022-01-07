package com.example.demo.awx.inventory.service;

import com.example.demo.awx.inventory.entity.model.AwxInventory;
import com.example.demo.awx.inventory.service.model.AwxInventoryCreateRequest;
import com.example.demo.awx.organization.entity.model.AwxOrganization;
import com.example.demo.sample.SampleBuilder;
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
public class AwxInventoryServiceCreateTest {

    @Autowired
    private AwxInventoryService awxInventoryService;

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
    public void whenCreatedHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> awxInventoryService.handleCreate(null));
    }

    @Test
    public void whenCreatedIsValidThenReturnNonNull() {

        AwxInventoryCreateRequest request = AwxInventoryCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .build();

        Assertions.assertNotNull(awxInventoryService.handleCreate(request));
    }

    @Test
    public void whenCreatedHasAwxOrganizationIdThenReturnNonNull() {

        AwxInventoryCreateRequest request = AwxInventoryCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .build();

        AwxInventory awxInventory = awxInventoryService.handleCreate(request);

        Assertions.assertNotNull(awxInventory);
    }

    @Test
    public void whenCreatedHasNullAwxOrganizationThrowException() {

        AwxInventoryCreateRequest request = AwxInventoryCreateRequest.builder()
                .awxOrganizationId(null)
                .awxId(1L)
                .name("name")
                .description("description")
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> awxInventoryService.handleCreate(request));
    }

    @Test
    public void whenCreatedHasAwxIdThenReturnAwxId() {

        AwxInventoryCreateRequest request = AwxInventoryCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(2L)
                .name("name")
                .description("description")
                .build();

        AwxInventory awxInventory = awxInventoryService.handleCreate(request);

        Assertions.assertEquals(2L, awxInventory.getAwxId());
    }

    @Test
    public void whenCreatedHasNullAwxIdThenThrowException() {

        AwxInventoryCreateRequest request = AwxInventoryCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(null)
                .name("name")
                .description("description")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxInventoryService.handleCreate(request));
    }

    @Test
    public void whenCreatedHasNameThenReturnName() {

        AwxInventoryCreateRequest request = AwxInventoryCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("test name")
                .description("description")
                .build();

        AwxInventory awxInventory = awxInventoryService.handleCreate(request);

        Assertions.assertEquals("test name", awxInventory.getName());
    }

    @Test
    public void whenCreatedHasNullNameThenThrowException() {

        AwxInventoryCreateRequest request = AwxInventoryCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name(null)
                .description("description")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxInventoryService.handleCreate(request));
    }

    @Test
    public void whenCreatedHasDescriptionThenReturnDescription() {

        AwxInventoryCreateRequest request = AwxInventoryCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("test description")
                .build();

        AwxInventory awxInventory = awxInventoryService.handleCreate(request);

        Assertions.assertEquals("test description", awxInventory.getDescription());
    }

    @Test
    public void whenCreatedHasNullDescriptionThenReturnNull() {

        AwxInventoryCreateRequest request = AwxInventoryCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description(null)
                .build();

        AwxInventory awxInventory = awxInventoryService.handleCreate(request);

        Assertions.assertNull(awxInventory.getDescription());
    }
}
