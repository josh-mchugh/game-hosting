package com.example.demo.ovh.flavor.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FlavorCreatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasRegionIdThenReturnRegionId() {

        UUID regionId = UUID.randomUUID();

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .regionId(regionId)
                .build();

        Assertions.assertEquals(regionId, event.getRegionId());
    }

    @Test
    public void whenEventHasOvhIdThenReturnOvhId() {

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .ovhId("ovhId")
                .build();

        Assertions.assertEquals("ovhId", event.getOvhId());
    }

    @Test
    public void whenEventHasNameThenReturnName() {

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", event.getName());
    }

    @Test
    public void whenEventHasTypeThenReturnType() {

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .type("type")
                .build();

        Assertions.assertEquals("type", event.getType());
    }

    @Test
    public void whenEventHasAvailableThenReturnAvailable() {

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .available(true)
                .build();

        Assertions.assertTrue(event.getAvailable());
    }

    @Test
    public void whenEventHasHourlyThenReturnHourly() {

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .hourly("hourly")
                .build();

        Assertions.assertEquals("hourly", event.getHourly());
    }

    @Test
    public void whenEventHasMonthlyThenReturnMonthly() {

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .monthly("monthly")
                .build();

        Assertions.assertEquals("monthly", event.getMonthly());
    }

    @Test
    public void whenEventHasQuotaThenReturnQuota() {

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .quota(1)
                .build();

        Assertions.assertEquals(1, event.getQuota());
    }

    @Test
    public void whenEventHasOsTypeThenReturnOsType() {

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .osType("osType")
                .build();

        Assertions.assertEquals("osType", event.getOsType());
    }

    @Test
    public void whenEventHasVcpusThenReturnVcpus() {

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .vcpus(1)
                .build();

        Assertions.assertEquals(1, event.getVcpus());
    }

    @Test
    public void whenEventHasRamThenReturnRam() {

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .ram(1)
                .build();

        Assertions.assertEquals(1, event.getRam());
    }

    @Test
    public void whenEventHasDiskThenReturnDisk() {

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .disk(1)
                .build();

        Assertions.assertEquals(1, event.getDisk());
    }

    @Test
    public void whenEventHasInboundBandwidthThenReturnInboundBandwidth() {

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .inboundBandwidth(1)
                .build();

        Assertions.assertEquals(1, event.getInboundBandwidth());
    }

    @Test
    public void whenEventHasOutboundBandwidthThenReturnOutboundBandwidth() {

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .outboundBandwidth(1)
                .build();

        Assertions.assertEquals(1, event.getOutboundBandwidth());
    }

    @Test
    public void whenEventToString() {

        FlavorCreatedEvent event = event();

        String expected = "FlavorCreatedEvent(id=d81fa8e6-5666-44c2-b1d2-0f4300ae1bbc, regionId=4688efae-72fb-4fcb-9315-60c372c50500, ovhId=ovhId, name=name, type=type, available=true, hourly=hourly, monthly=monthly, quota=1, osType=osType, vcpus=1, ram=1, disk=1, inboundBandwidth=1, outboundBandwidth=1)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        FlavorCreatedEvent event = event();

        Assertions.assertEquals(1341504840, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        FlavorCreatedEvent event1 = event();
        FlavorCreatedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        FlavorCreatedEvent event = event();

        Assertions.assertNotEquals(event, FlavorCreatedEvent.builder().build());
    }

    private FlavorCreatedEvent event() {

        return FlavorCreatedEvent.builder()
                .id(UUID.fromString("d81fa8e6-5666-44c2-b1d2-0f4300ae1bbc"))
                .regionId(UUID.fromString("4688efae-72fb-4fcb-9315-60c372c50500"))
                .ovhId("ovhId")
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
