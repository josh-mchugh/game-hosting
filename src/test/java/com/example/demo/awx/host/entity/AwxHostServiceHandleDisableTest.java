package com.example.demo.awx.host.entity;

import com.example.demo.awx.host.aggregate.event.AwxHostCreatedEvent;
import com.example.demo.awx.host.aggregate.event.AwxHostDisabledEvent;
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
public class AwxHostServiceHandleDisableTest {

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
    public void whenEntityEnabledIsTrueThenReturnEnabledFalse() {

        UUID id = UUID.randomUUID();

        AwxHostCreatedEvent createdEvent = AwxHostCreatedEvent.builder()
                .id(id)
                .awxInventoryId(data.getAwxInventory().getId())
                .instanceId(data.getInstance().getId())
                .hostId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();
        AwxHost awxHost = awxHostService.handleCreated(createdEvent);

        AwxHostDisabledEvent disabledEvent = new AwxHostDisabledEvent(id);
        AwxHost updatedHost = awxHostService.handleDisabled(disabledEvent);

        Assertions.assertTrue(awxHost.getEnabled());
        Assertions.assertFalse(updatedHost.getEnabled());
    }

    @Test
    public void whenEntityEnabledIsFalseThenReturnEnabledFalse() {

        UUID id = UUID.randomUUID();

        AwxHostCreatedEvent createdEvent = AwxHostCreatedEvent.builder()
                .id(id)
                .awxInventoryId(data.getAwxInventory().getId())
                .instanceId(data.getInstance().getId())
                .hostId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(false)
                .build();
        AwxHost awxHost = awxHostService.handleCreated(createdEvent);

        AwxHostDisabledEvent disabledEvent = new AwxHostDisabledEvent(id);
        AwxHost updatedHost = awxHostService.handleDisabled(disabledEvent);

        Assertions.assertFalse(awxHost.getEnabled());
        Assertions.assertFalse(updatedHost.getEnabled());
    }

    @Test
    public void whenHandleDisableHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> awxHostService.handleDisabled(null));
    }

    @Test
    public void whenHandleDisableHasInvalidHostIdThenThrowException() {

        AwxHostDisabledEvent event = new AwxHostDisabledEvent(UUID.randomUUID());

        Assertions.assertThrows(NullPointerException.class, () -> awxHostService.handleDisabled(event));
    }
}
