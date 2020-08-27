package com.example.demo.awx.inventory.service;

import com.example.demo.awx.inventory.model.AwxInventory;
import com.example.demo.awx.inventory.service.model.AwxInventoryCreateRequest;
import com.example.demo.awx.organization.model.AwxOrganization;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.util.TestAwxInventoryCreateRequest;
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
public class AwxInventoryServiceCreateRequestTest {

    @Autowired
    private IAwxInventoryService awxInventoryService;

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
    public void whenCreateRequestHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> awxInventoryService.handleCreateRequest(null));
    }

    @Test
    public void whenCreateRequestIsValidThenReturnNonNull() {

        AwxInventoryCreateRequest request = TestAwxInventoryCreateRequest.builder()
                .organization(awxOrganization)
                .build();

        Assertions.assertNotNull(awxInventoryService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestIsValidThenReturnId() {

        AwxInventoryCreateRequest request = TestAwxInventoryCreateRequest.builder()
                .organization(awxOrganization)
                .build();
        AwxInventory awxInventory = awxInventoryService.handleCreateRequest(request);

        Assertions.assertNotNull(awxInventory.getId());
    }

    @Test
    public void whenCreateRequestHasNullOrganizationThrowException() {

        AwxInventoryCreateRequest request = TestAwxInventoryCreateRequest.builder()
                .organizationId(null)
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxInventoryService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasInventoryIdThenReturnInventoryId() {

        AwxInventoryCreateRequest request = TestAwxInventoryCreateRequest.builder()
                .organization(awxOrganization)
                .inventoryId(2L)
                .build();

        AwxInventory awxInventory = awxInventoryService.handleCreateRequest(request);

        Assertions.assertEquals(2L, awxInventory.getInventoryId());
    }

    @Test
    public void whenCreateRequestHasNullInventoryIdThenThrowException() {

        AwxInventoryCreateRequest request = TestAwxInventoryCreateRequest.builder()
                .organization(awxOrganization)
                .inventoryId(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxInventoryService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasNameThenReturnName() {

        AwxInventoryCreateRequest request = TestAwxInventoryCreateRequest.builder()
                .organization(awxOrganization)
                .name("test name")
                .build();

        AwxInventory awxInventory = awxInventoryService.handleCreateRequest(request);

        Assertions.assertEquals("test name", awxInventory.getName());
    }

    @Test
    public void whenCreateRequestHasNullNameThenThrowException() {

        AwxInventoryCreateRequest request = TestAwxInventoryCreateRequest.builder()
                .organization(awxOrganization)
                .name(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxInventoryService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasDescriptionThenReturnDescription() {

        AwxInventoryCreateRequest request = TestAwxInventoryCreateRequest.builder()
                .organization(awxOrganization)
                .description("test description")
                .build();

        AwxInventory awxInventory = awxInventoryService.handleCreateRequest(request);

        Assertions.assertEquals("test description", awxInventory.getDescription());
    }

    @Test
    public void whenCreateRequestHasNullDescriptionThenReturnNull() {

        AwxInventoryCreateRequest request = TestAwxInventoryCreateRequest.builder()
                .organization(awxOrganization)
                .description(null)
                .build();

        AwxInventory awxInventory = awxInventoryService.handleCreateRequest(request);

        Assertions.assertNull(awxInventory.getDescription());
    }
}
