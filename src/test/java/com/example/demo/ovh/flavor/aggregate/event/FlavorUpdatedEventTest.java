package com.example.demo.ovh.flavor.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FlavorUpdatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasNameThenReturnName() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", event.getName());
    }

    @Test
    public void whenEventHasTypeThenReturnType() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .type("type")
                .build();

        Assertions.assertEquals("type", event.getType());
    }

    @Test
    public void whenEventHasAvailableThenReturnAvailable() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .available(true)
                .build();

        Assertions.assertTrue(event.getAvailable());
    }

    @Test
    public void whenEventHasHourlyThenReturnHourly() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .hourly("hourly")
                .build();

        Assertions.assertEquals("hourly", event.getHourly());
    }

    @Test
    public void whenEventHasMonthlyThenReturnMonthly() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .monthly("monthly")
                .build();

        Assertions.assertEquals("monthly", event.getMonthly());
    }

    @Test
    public void whenEventHasQuotaThenReturnQuota() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .quota(1)
                .build();

        Assertions.assertEquals(1, event.getQuota());
    }

    @Test
    public void whenEventHasOsTypeThenReturnOsType() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .osType("osType")
                .build();

        Assertions.assertEquals("osType", event.getOsType());
    }

    @Test
    public void whenEventHasVcpusThenReturnVcpus() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .vcpus(1)
                .build();

        Assertions.assertEquals(1, event.getVcpus());
    }

    @Test
    public void whenEventHasRamThenReturnRam() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .ram(1)
                .build();

        Assertions.assertEquals(1, event.getRam());
    }

    @Test
    public void whenEventHasDiskThenReturnDisk() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .disk(1)
                .build();

        Assertions.assertEquals(1, event.getDisk());
    }

    @Test
    public void whenEventHasInboundBandwidthThenReturnInboundBandwidth() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .inboundBandwidth(1)
                .build();

        Assertions.assertEquals(1, event.getInboundBandwidth());
    }

    @Test
    public void whenEventHasOutboundBandwidthThenReturnOutboundBandwidth() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .outboundBandwidth(1)
                .build();

        Assertions.assertEquals(1, event.getOutboundBandwidth());
    }

    @Test
    public void whenEventToString() {

        FlavorUpdatedEvent event = event();

        String expected = "FlavorUpdatedEvent(id=d81fa8e6-5666-44c2-b1d2-0f4300ae1bbc, name=name, type=type, available=true, hourly=hourly, monthly=monthly, quota=1, osType=osType, vcpus=1, ram=1, disk=1, inboundBandwidth=1, outboundBandwidth=1)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        FlavorUpdatedEvent event = event();

        Assertions.assertEquals(-2019563462, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        FlavorUpdatedEvent event1 = event();
        FlavorUpdatedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        FlavorUpdatedEvent event = event();

        Assertions.assertNotEquals(event, FlavorUpdatedEvent.builder().build());
    }

    private FlavorUpdatedEvent event() {

        return FlavorUpdatedEvent.builder()
                .id(UUID.fromString("d81fa8e6-5666-44c2-b1d2-0f4300ae1bbc"))
                .name("name")
                .type("type")
                .available(true)
                .hourly("hourly")
                .monthly("monthly")
                .quota(1)
                .osType("osType")
                .vcpus(1)
                .ram(1)
                .disk(1)
                .inboundBandwidth(1)
                .outboundBandwidth(1)
                .build();
    }
}
