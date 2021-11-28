package com.example.demo.awx.host.entity.service;

import com.example.demo.awx.host.aggregate.event.AwxHostCreatedEvent;
import com.example.demo.awx.host.entity.model.AwxHost;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
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
public class AwxHostServiceCreatedTest {

    @Autowired
    private AwxHostService awxHostService;

    @Autowired
    private SampleBuilder sampleBuilder;

    private SampleData data;

    @BeforeEach
    public void setup () {

        data = sampleBuilder.builder()
                .user()
                .region()
                .flavor()
                .image()
                .project()
                .credential()
                .instanceGroup()
                .instance()
                .awxOrganization()
                .awxInventory()
                .awxHost()
                .build();
    }

    @Test
    public void whenCreatedHasNullParamThenThrowException () {

        Assertions.assertThrows(NullPointerException.class, () -> awxHostService.handleCreated(null));
    }

    @Test
    public void whenCreatedIsValidThenReturnNotNull() {

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxInventoryId(data.getAwxInventory().getId())
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();

        AwxHost awxHost = awxHostService.handleCreated(event);

        Assertions.assertNotNull(awxHost);
    }

    @Test
    public void whenCreatedIsValidThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .id(id)
                .awxInventoryId(data.getAwxInventory().getId())
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();

        AwxHost awxHost = awxHostService.handleCreated(event);

        Assertions.assertEquals(id.toString(), awxHost.getId().toString());
    }

    @Test
    public void whenCreatedHasNullInventoryIdThrowException() {

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxInventoryId(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> awxHostService.handleCreated(event));
    }

    @Test
    public void whenCreatedHasNullInstanceIdThenThrowException() {

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxInventoryId(data.getAwxInventory().getId())
                .instanceId(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> awxHostService.handleCreated(event));
    }

    @Test
    public void whenCreatedHasAwxIdThenReturnAwxId() {

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxInventoryId(data.getAwxInventory().getId())
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();

        AwxHost awxHost = awxHostService.handleCreated(event);

        Assertions.assertEquals(1L, awxHost.getAwxId());
    }

    @Test
    public void whenCreatedHasNullHostIdThenReturnThrowException() {

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxInventoryId(data.getAwxInventory().getId())
                .instanceId(data.getInstance().getId())
                .awxId(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxHostService.handleCreated(event));
    }

    @Test
    public void whenCreatedHasHostnameThenReturnHostName() {

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxInventoryId(data.getAwxInventory().getId())
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();

        AwxHost awxHost = awxHostService.handleCreated(event);

        Assertions.assertEquals("hostname", awxHost.getHostname());
    }

    @Test
    public void whenCreatedHasNullHostnameThenThrowException() {

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxInventoryId(data.getAwxInventory().getId())
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxHostService.handleCreated(event));
    }

    @Test
    public void whenCreatedHasDescriptionThenReturnDescription() {

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxInventoryId(data.getAwxInventory().getId())
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();

        AwxHost awxHost = awxHostService.handleCreated(event);

        Assertions.assertEquals("description", awxHost.getDescription());
    }

    @Test
    public void whenCreatedHasNullDescriptionThenReturnNull() {

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxInventoryId(data.getAwxInventory().getId())
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description(null)
                .enabled(true)
                .build();

        AwxHost awxHost = awxHostService.handleCreated(event);

        Assertions.assertNull(awxHost.getDescription());
    }

    @Test
    public void whenCreatedHasEnabledTrueThenReturnTrue() {

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxInventoryId(data.getAwxInventory().getId())
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();

        AwxHost awxHost = awxHostService.handleCreated(event);

        Assertions.assertTrue(awxHost.getEnabled());
    }

    @Test
    public void whenCreatedHasEnabledFalseThenReturnFalse() {

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxInventoryId(data.getAwxInventory().getId())
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(false)
                .build();

        AwxHost awxHost = awxHostService.handleCreated(event);

        Assertions.assertFalse(awxHost.getEnabled());
    }

    @Test
    public void whenCreatedHasNullEnabledThenThrowException() {

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxInventoryId(data.getAwxInventory().getId())
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxHostService.handleCreated(event));
    }
}
