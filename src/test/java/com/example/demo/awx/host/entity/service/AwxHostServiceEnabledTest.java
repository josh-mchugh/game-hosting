package com.example.demo.awx.host.entity.service;

import com.example.demo.awx.host.aggregate.event.AwxHostCreatedEvent;
import com.example.demo.awx.host.aggregate.event.AwxHostEnabledEvent;
import com.example.demo.awx.host.entity.model.AwxHost;
import com.example.demo.awx.host.entity.service.IAwxHostService;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxHostServiceEnabledTest {

    @Autowired
    private IAwxHostService awxHostService;

    @Autowired
    private SampleBuilder sampleBuilder;

    private SampleData data;

    @BeforeEach
    public void setup() {

        data = sampleBuilder.builder()
                .region()
                .flavor()
                .image()
                .credential()
                .user()
                .project()
                .instanceGroup()
                .instance()
                .awxOrganization()
                .awxInventory()
                .build();
    }

    @Test
    public void whenEntityEnabledIsFalseThenReturnEnabledTrue() {

        UUID id = UUID.randomUUID();

        AwxHostCreatedEvent createdEvent = AwxHostCreatedEvent.builder()
                .id(id)
                .awxInventoryId(data.getAwxInventory().getId())
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(false)
                .build();
        AwxHost awxHost = awxHostService.handleCreated(createdEvent);

        AwxHostEnabledEvent enabledEvent = new AwxHostEnabledEvent(id);
        AwxHost updatedHost = awxHostService.handleEnabled(enabledEvent);

        Assertions.assertFalse(awxHost.getEnabled());
        Assertions.assertTrue(updatedHost.getEnabled());
    }

    @Test
    public void whenEntityEnabledIsTrueThenReturnEnabledTrue() {

        UUID id = UUID.randomUUID();

        AwxHostCreatedEvent createdEvent = AwxHostCreatedEvent.builder()
                .id(id)
                .awxInventoryId(data.getAwxInventory().getId())
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();
        AwxHost awxHost = awxHostService.handleCreated(createdEvent);

        AwxHostEnabledEvent enabledEvent = new AwxHostEnabledEvent(id);
        AwxHost updatedHost = awxHostService.handleEnabled(enabledEvent);

        Assertions.assertTrue(awxHost.getEnabled());
        Assertions.assertTrue(updatedHost.getEnabled());
    }

    @Test
    public void whenHandleEnableHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> awxHostService.handleEnabled(null));
    }

    @Test
    public void whenHandleEnableHasInvalidHostIdThenThrowException() {

        AwxHostEnabledEvent event = new AwxHostEnabledEvent(UUID.randomUUID());

        Assertions.assertThrows(NullPointerException.class, () -> awxHostService.handleEnabled(event));
    }
}
