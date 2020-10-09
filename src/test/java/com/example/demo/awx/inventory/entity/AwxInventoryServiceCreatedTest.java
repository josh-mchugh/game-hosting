package com.example.demo.awx.inventory.entity;

import com.example.demo.awx.inventory.aggregate.event.AwxInventoryCreatedEvent;
import com.example.demo.awx.inventory.entity.model.AwxInventory;
import com.example.demo.awx.inventory.entity.service.IAwxInventoryService;
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
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxInventoryServiceCreatedTest {

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
    public void whenCreatedHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> awxInventoryService.handleCreated(null));
    }

    @Test
    public void whenCreatedIsValidThenReturnNonNull() {

        AwxInventoryCreatedEvent event = AwxInventoryCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .inventoryId(1L)
                .name("name")
                .description("description")
                .build();

        Assertions.assertNotNull(awxInventoryService.handleCreated(event));
    }

    @Test
    public void whenCreatedIsValidThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxInventoryCreatedEvent event = AwxInventoryCreatedEvent.builder()
                .id(id)
                .organizationId(awxOrganization.getOrganizationId())
                .inventoryId(1L)
                .name("name")
                .description("description")
                .build();

        AwxInventory awxInventory = awxInventoryService.handleCreated(event);

        Assertions.assertEquals(id.toString(), awxInventory.getId());
    }

    @Test
    public void whenCreatedHasOrganizationIdThenReturnNonNull() {

        AwxInventoryCreatedEvent event = AwxInventoryCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .inventoryId(1L)
                .name("name")
                .description("description")
                .build();

        AwxInventory awxInventory = awxInventoryService.handleCreated(event);

        Assertions.assertNotNull(awxInventory);
    }

    @Test
    public void whenCreatedHasNullOrganizationThrowException() {

        AwxInventoryCreatedEvent event = AwxInventoryCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(null)
                .inventoryId(1L)
                .name("name")
                .description("description")
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxInventoryService.handleCreated(event));
    }

    @Test
    public void whenCreatedHasInventoryIdThenReturnInventoryId() {

        AwxInventoryCreatedEvent event = AwxInventoryCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .inventoryId(2L)
                .name("name")
                .description("description")
                .build();

        AwxInventory awxInventory = awxInventoryService.handleCreated(event);

        Assertions.assertEquals(2L, awxInventory.getInventoryId());
    }

    @Test
    public void whenCreatedHasNullInventoryIdThenThrowException() {

        AwxInventoryCreatedEvent event = AwxInventoryCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .inventoryId(null)
                .name("name")
                .description("description")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxInventoryService.handleCreated(event));
    }

    @Test
    public void whenCreatedHasNameThenReturnName() {

        AwxInventoryCreatedEvent event = AwxInventoryCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .inventoryId(1L)
                .name("test name")
                .description("description")
                .build();

        AwxInventory awxInventory = awxInventoryService.handleCreated(event);

        Assertions.assertEquals("test name", awxInventory.getName());
    }

    @Test
    public void whenCreatedHasNullNameThenThrowException() {

        AwxInventoryCreatedEvent event = AwxInventoryCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .inventoryId(1L)
                .name(null)
                .description("description")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxInventoryService.handleCreated(event));
    }

    @Test
    public void whenCreatedHasDescriptionThenReturnDescription() {

        AwxInventoryCreatedEvent event = AwxInventoryCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .inventoryId(1L)
                .name("name")
                .description("test description")
                .build();

        AwxInventory awxInventory = awxInventoryService.handleCreated(event);

        Assertions.assertEquals("test description", awxInventory.getDescription());
    }

    @Test
    public void whenCreatedHasNullDescriptionThenReturnNull() {

        AwxInventoryCreatedEvent event = AwxInventoryCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .inventoryId(1L)
                .name("name")
                .description(null)
                .build();

        AwxInventory awxInventory = awxInventoryService.handleCreated(event);

        Assertions.assertNull(awxInventory.getDescription());
    }
}
