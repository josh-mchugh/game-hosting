package com.example.demo.ovh.instance.aggregate.event;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class InstanceUpdatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        InstanceUpdatedEvent event = InstanceUpdatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasNameThenReturnName() {

        InstanceUpdatedEvent event = InstanceUpdatedEvent.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", event.getName());
    }

    @Test
    public void whenEventHasStatusThenReturnStatus() {

        InstanceUpdatedEvent event = InstanceUpdatedEvent.builder()
                .status(InstanceStatus.ACTIVE)
                .build();

        Assertions.assertEquals(InstanceStatus.ACTIVE, event.getStatus());
    }

    @Test
    public void whenEventHasIp4AddressThenReturnIp4Address() {

        InstanceUpdatedEvent event = InstanceUpdatedEvent.builder()
                .ip4Address("ip4Address")
                .build();

        Assertions.assertEquals("ip4Address", event.getIp4Address());
    }

    @Test
    public void whenEventHasIp6AddressThenReturnIp6Address() {

        InstanceUpdatedEvent event = InstanceUpdatedEvent.builder()
                .ip6Address("ip6Address")
                .build();

        Assertions.assertEquals("ip6Address", event.getIp6Address());
    }

    @Test
    public void whenEventHasInstanceCreatedDateThenReturnInstanceCreatedDate() {

        LocalDateTime instanceCreatedDate = LocalDateTime.now();

        InstanceUpdatedEvent event = InstanceUpdatedEvent.builder()
                .instanceCreatedDate(instanceCreatedDate)
                .build();

        Assertions.assertEquals(instanceCreatedDate, event.getInstanceCreatedDate());
    }

    @Test
    public void whenEventToString() {

        InstanceUpdatedEvent event = InstanceUpdatedEvent.builder()
                .id(UUID.fromString("b5f1bccc-9df4-458f-913d-b7d157335924"))
                .name("name")
                .ip4Address("ip4Address")
                .ip6Address("ip6Address")
                .instanceCreatedDate(LocalDateTime.of(2020, 11, 28, 22, 57))
                .build();

        String expected = "InstanceUpdatedEvent(id=b5f1bccc-9df4-458f-913d-b7d157335924, name=name, status=null, ip4Address=ip4Address, ip6Address=ip6Address, instanceCreatedDate=2020-11-28T22:57)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        InstanceUpdatedEvent event = event();

        Assertions.assertEquals(-875656571, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        InstanceUpdatedEvent event1 = event();
        InstanceUpdatedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        InstanceUpdatedEvent event = event();

        Assertions.assertNotEquals(event, InstanceUpdatedEvent.builder().build());
    }

    private InstanceUpdatedEvent event() {

        return InstanceUpdatedEvent.builder()
                .id(UUID.fromString("b5f1bccc-9df4-458f-913d-b7d157335924"))
                .name("name")
                .ip4Address("ip4Address")
                .ip6Address("ip6Address")
                .instanceCreatedDate(LocalDateTime.of(2020, 11, 28, 22, 57))
                .build();
    }
}
