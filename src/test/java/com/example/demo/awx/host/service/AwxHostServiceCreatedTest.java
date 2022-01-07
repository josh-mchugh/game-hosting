package com.example.demo.awx.host.service;

import com.example.demo.awx.host.entity.model.AwxHost;
import com.example.demo.awx.host.service.model.AwxHostCreateRequest;
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

        Assertions.assertThrows(NullPointerException.class, () -> awxHostService.handleCreate(null));
    }

    @Test
    public void whenCreatedIsValidThenReturnNotNull() {

        AwxHostCreateRequest request = AwxHostCreateRequest.builder()
                .awxInventoryId(UUID.fromString(data.getAwxInventory().getId()))
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();

        AwxHost awxHost = awxHostService.handleCreate(request);

        Assertions.assertNotNull(awxHost);
    }

    @Test
    public void whenCreatedIsValidThenReturnId() {

        AwxHostCreateRequest request = AwxHostCreateRequest.builder()
                .awxInventoryId(UUID.fromString(data.getAwxInventory().getId()))
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();

        AwxHost awxHost = awxHostService.handleCreate(request);

        Assertions.assertNotNull(awxHost.getId());
    }

    @Test
    public void whenCreatedHasNullInventoryIdThrowException() {

        AwxHostCreateRequest request = AwxHostCreateRequest.builder()
                .awxInventoryId(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> awxHostService.handleCreate(request));
    }

    @Test
    public void whenCreatedHasNullInstanceIdThenThrowException() {

        AwxHostCreateRequest request = AwxHostCreateRequest.builder()
                .awxInventoryId(UUID.fromString(data.getAwxInventory().getId()))
                .instanceId(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> awxHostService.handleCreate(request));
    }

    @Test
    public void whenCreatedHasAwxIdThenReturnAwxId() {

        AwxHostCreateRequest request = AwxHostCreateRequest.builder()
                .awxInventoryId(UUID.fromString(data.getAwxInventory().getId()))
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();

        AwxHost awxHost = awxHostService.handleCreate(request);

        Assertions.assertEquals(1L, awxHost.getAwxId());
    }

    @Test
    public void whenCreatedHasNullHostIdThenReturnThrowException() {

        AwxHostCreateRequest request = AwxHostCreateRequest.builder()
                .awxInventoryId(UUID.fromString(data.getAwxInventory().getId()))
                .instanceId(data.getInstance().getId())
                .awxId(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxHostService.handleCreate(request));
    }

    @Test
    public void whenCreatedHasHostnameThenReturnHostName() {

        AwxHostCreateRequest request = AwxHostCreateRequest.builder()
                .awxInventoryId(UUID.fromString(data.getAwxInventory().getId()))
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();

        AwxHost awxHost = awxHostService.handleCreate(request);

        Assertions.assertEquals("hostname", awxHost.getHostname());
    }

    @Test
    public void whenCreatedHasNullHostnameThenThrowException() {

        AwxHostCreateRequest request = AwxHostCreateRequest.builder()
                .awxInventoryId(UUID.fromString(data.getAwxInventory().getId()))
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxHostService.handleCreate(request));
    }

    @Test
    public void whenCreatedHasDescriptionThenReturnDescription() {

        AwxHostCreateRequest request = AwxHostCreateRequest.builder()
                .awxInventoryId(UUID.fromString(data.getAwxInventory().getId()))
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();

        AwxHost awxHost = awxHostService.handleCreate(request);

        Assertions.assertEquals("description", awxHost.getDescription());
    }

    @Test
    public void whenCreatedHasNullDescriptionThenReturnNull() {

        AwxHostCreateRequest request = AwxHostCreateRequest.builder()
                .awxInventoryId(UUID.fromString(data.getAwxInventory().getId()))
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description(null)
                .enabled(true)
                .build();

        AwxHost awxHost = awxHostService.handleCreate(request);

        Assertions.assertNull(awxHost.getDescription());
    }

    @Test
    public void whenCreatedHasEnabledTrueThenReturnTrue() {

        AwxHostCreateRequest request = AwxHostCreateRequest.builder()
                .awxInventoryId(UUID.fromString(data.getAwxInventory().getId()))
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();

        AwxHost awxHost = awxHostService.handleCreate(request);

        Assertions.assertTrue(awxHost.getEnabled());
    }

    @Test
    public void whenCreatedHasEnabledFalseThenReturnFalse() {

        AwxHostCreateRequest request = AwxHostCreateRequest.builder()
                .awxInventoryId(UUID.fromString(data.getAwxInventory().getId()))
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(false)
                .build();

        AwxHost awxHost = awxHostService.handleCreate(request);

        Assertions.assertFalse(awxHost.getEnabled());
    }

    @Test
    public void whenCreatedHasNullEnabledThenThrowException() {

        AwxHostCreateRequest request = AwxHostCreateRequest.builder()
                .awxInventoryId(UUID.fromString(data.getAwxInventory().getId()))
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxHostService.handleCreate(request));
    }
}
