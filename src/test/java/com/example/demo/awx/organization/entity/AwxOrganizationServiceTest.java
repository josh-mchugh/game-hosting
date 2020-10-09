package com.example.demo.awx.organization.entity;

import com.example.demo.awx.organization.aggregate.event.AwxOrganizationCreatedEvent;
import com.example.demo.awx.organization.entity.model.AwxOrganization;
import com.example.demo.awx.organization.entity.service.IAwxOrganizationService;
import org.junit.jupiter.api.Assertions;
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
public class AwxOrganizationServiceTest {

    @Autowired
    private IAwxOrganizationService awxOrganizationService;

    @Test
    public void whenEntityHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .id(id)
                .organizationId(1L)
                .name("name")
                .description("description")
                .build();

        AwxOrganization awxOrganization = awxOrganizationService.handleCreated(event);

        Assertions.assertEquals(id.toString(), awxOrganization.getId());
    }

    @Test
    public void whenEntityHasNullIdThenThrowException() {

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .id(null)
                .organizationId(1L)
                .name("name")
                .description("description")
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> awxOrganizationService.handleCreated(event));
    }

    @Test
    public void whenEntityHasNullOrganizationIdThenThrowException() {

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(null)
                .name("name")
                .description("description")
                .build();

        Assertions.assertThrows(PersistenceException.class, ()-> awxOrganizationService.handleCreated(event));
    }

    @Test
    public void whenEntityHasOrganizationIdThenReturnOrganizationId() {

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .name("name")
                .description("description")
                .build();

        AwxOrganization awxOrganization = awxOrganizationService.handleCreated(event);

        Assertions.assertEquals(1L, awxOrganization.getOrganizationId());
    }

    @Test
    public void whenEntityHasNullNameThenThrowException() {

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .name(null)
                .description("description")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxOrganizationService.handleCreated(event));
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .name("name")
                .description("description")
                .build();

        AwxOrganization awxOrganization = awxOrganizationService.handleCreated(event);

        Assertions.assertEquals("name", awxOrganization.getName());
    }

    @Test
    public void whenEntityHasNullDescriptionThenReturnNull() {

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .name("name")
                .description(null)
                .build();

        AwxOrganization awxOrganization = awxOrganizationService.handleCreated(event);

        Assertions.assertNull(awxOrganization.getDescription());
    }

    @Test
    public void whenEntityHasDescriptionThenReturnDescription() {

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .name("name")
                .description("description")
                .build();

        AwxOrganization awxOrganization = awxOrganizationService.handleCreated(event);

        Assertions.assertEquals("description", awxOrganization.getDescription());
    }
}
