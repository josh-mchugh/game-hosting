package com.example.demo.ovh.instance.entity.service;

import com.example.demo.ovh.instance.aggregate.event.InstanceUpdatedEvent;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.ovh.instance.entity.model.Instance;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class InstanceServiceUpdatedTest {

    @Autowired
    private IInstanceService instanceService;

    @Autowired
    private SampleBuilder sampleBuilder;

    private SampleData data;

    @BeforeEach
    public void setup() {

        data = sampleBuilder.createDefault();
    }

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> instanceService.handleCreated(null));
    }

    @Test
    public void whenEventIdThenReturnId() {

        InstanceUpdatedEvent event = InstanceUpdatedEvent.builder()
                .id(UUID.fromString(data.getInstance().getId()))
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .ip4Address("ip4Address")
                .ip6Address("ip6Address")
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        Instance instance = instanceService.handleUpdated(event);

        Assertions.assertEquals(data.getInstance().getId(), instance.getId());
    }

    @Test
    public void whenEventHasNullIdThenExpectException() {

        InstanceUpdatedEvent event = InstanceUpdatedEvent.builder()
                .id(null)
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .ip4Address("ip4Address")
                .ip6Address("ip6Address")
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> instanceService.handleUpdated(event));
    }

    @Test
    public void whenEventHasInvalidIdThenExpectException() {

        InstanceUpdatedEvent event = InstanceUpdatedEvent.builder()
                .id(UUID.randomUUID())
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .ip4Address("ip4Address")
                .ip6Address("ip6Address")
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> instanceService.handleUpdated(event));
    }

    @Test
    public void whenEventHasNameThenReturnName() {

        InstanceUpdatedEvent event = InstanceUpdatedEvent.builder()
                .id(UUID.fromString(data.getInstance().getId()))
                .name("new-name")
                .build();

        Instance instance = instanceService.handleUpdated(event);

        Assertions.assertEquals("new-name", instance.getName());
    }

    @Test
    public void whenEventHasStatusThenReturnStatus() {

        InstanceUpdatedEvent event = InstanceUpdatedEvent.builder()
                .id(UUID.fromString(data.getInstance().getId()))
                .status(InstanceStatus.STOPPED)
                .build();

        Instance instance = instanceService.handleUpdated(event);

        Assertions.assertEquals(InstanceStatus.STOPPED, instance.getStatus());
    }

    @Test
    public void whenEventHasInstanceCreatedDateThenReturnInstanceCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        InstanceUpdatedEvent event = InstanceUpdatedEvent.builder()
                .id(UUID.fromString(data.getInstance().getId()))
                .instanceCreatedDate(createdDate)
                .build();

        Instance instance = instanceService.handleUpdated(event);

        Assertions.assertEquals(createdDate, instance.getInstanceCreatedDate());
    }

    @Test
    public void whenEventHadIp4AddressThenReturnIp4Address() {

        InstanceUpdatedEvent event = InstanceUpdatedEvent.builder()
                .id(UUID.fromString(data.getInstance().getId()))
                .ip4Address("new-ip-address")
                .build();

        Instance instance = instanceService.handleUpdated(event);

        Assertions.assertEquals("new-ip-address", instance.getIp4Address());
    }

    @Test
    public void whenEventHadIp6AddressThenReturnIp6Address() {

        InstanceUpdatedEvent event = InstanceUpdatedEvent.builder()
                .id(UUID.fromString(data.getInstance().getId()))
                .ip6Address("new-ip-address")
                .build();

        Instance instance = instanceService.handleUpdated(event);

        Assertions.assertEquals("new-ip-address", instance.getIp6Address());
    }
}
