package com.example.demo.awx.organization.entity.service;

import com.example.demo.awx.organization.aggregate.event.AwxOrganizationCreatedEvent;
import com.example.demo.awx.organization.entity.model.AwxOrganization;
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
public class AwxOrganizationServiceCreatedTest {

    @Autowired
    private IAwxOrganizationService awxOrganizationService;

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .id(id)
                .awxId(1L)
                .name("name")
                .description("description")
                .build();

        AwxOrganization awxOrganization = awxOrganizationService.handleCreated(event);

        Assertions.assertEquals(id.toString(), awxOrganization.getId());
    }

    @Test
    public void whenEventHasNullIdThenThrowException() {

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .id(null)
                .awxId(1L)
                .name("name")
                .description("description")
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> awxOrganizationService.handleCreated(event));
    }

    @Test
    public void whenEventHasNullAwxIdThenThrowException() {

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxId(null)
                .name("name")
                .description("description")
                .build();

        Assertions.assertThrows(PersistenceException.class, ()-> awxOrganizationService.handleCreated(event));
    }

    @Test
    public void whenEventHasAwxIdThenReturnAwxId() {

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description("description")
                .build();

        AwxOrganization awxOrganization = awxOrganizationService.handleCreated(event);

        Assertions.assertEquals(1L, awxOrganization.getAwxId());
    }

    @Test
    public void whenEventHasNullNameThenThrowException() {

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxId(1L)
                .name(null)
                .description("description")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxOrganizationService.handleCreated(event));
    }

    @Test
    public void whenEventHasNameThenReturnName() {

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description("description")
                .build();

        AwxOrganization awxOrganization = awxOrganizationService.handleCreated(event);

        Assertions.assertEquals("name", awxOrganization.getName());
    }

    @Test
    public void whenEventHasNullDescriptionThenReturnNull() {

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description(null)
                .build();

        AwxOrganization awxOrganization = awxOrganizationService.handleCreated(event);

        Assertions.assertNull(awxOrganization.getDescription());
    }

    @Test
    public void whenEventHasDescriptionThenReturnDescription() {

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description("description")
                .build();

        AwxOrganization awxOrganization = awxOrganizationService.handleCreated(event);

        Assertions.assertEquals("description", awxOrganization.getDescription());
    }
}
