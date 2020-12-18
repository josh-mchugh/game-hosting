package com.example.demo.ovh.flavor.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FlavorCreateCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FlavorCreateCommand command = FlavorCreateCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasRegionIdThenReturnRegionId() {

        FlavorCreateCommand command = FlavorCreateCommand.builder()
                .regionId("regionId")
                .build();

        Assertions.assertEquals("regionId", command.getRegionId());
    }

    @Test
    public void whenCommandHasOvhIdThenReturnOvhId() {

        FlavorCreateCommand command = FlavorCreateCommand.builder()
                .ovhId("ovhId")
                .build();

        Assertions.assertEquals("ovhId", command.getOvhId());
    }

    @Test
    public void whenCommandHasNameThenReturnName() {

        FlavorCreateCommand command = FlavorCreateCommand.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", command.getName());
    }

    @Test
    public void whenCommandHasTypeThenReturnType() {

        FlavorCreateCommand command = FlavorCreateCommand.builder()
                .type("type")
                .build();

        Assertions.assertEquals("type", command.getType());
    }

    @Test
    public void whenCommandHasAvailableThenReturnAvailable() {

        FlavorCreateCommand command = FlavorCreateCommand.builder()
                .available(true)
                .build();

        Assertions.assertTrue(command.getAvailable());
    }

    @Test
    public void whenCommandHasHourlyThenReturnHourly() {

        FlavorCreateCommand command = FlavorCreateCommand.builder()
                .hourly("hourly")
                .build();

        Assertions.assertEquals("hourly", command.getHourly());
    }

    @Test
    public void whenCommandHasMonthlyThenReturnMonthly() {

        FlavorCreateCommand command = FlavorCreateCommand.builder()
                .monthly("monthly")
                .build();

        Assertions.assertEquals("monthly", command.getMonthly());
    }

    @Test
    public void whenCommandHasQuotaThenReturnQuota() {

        FlavorCreateCommand command = FlavorCreateCommand.builder()
                .quota(1)
                .build();

        Assertions.assertEquals(1, command.getQuota());
    }

    @Test
    public void whenCommandHasOsTypeThenReturnOsType() {

        FlavorCreateCommand command = FlavorCreateCommand.builder()
                .osType("osType")
                .build();

        Assertions.assertEquals("osType", command.getOsType());
    }

    @Test
    public void whenCommandHasVcpusThenReturnVcpus() {

        FlavorCreateCommand command = FlavorCreateCommand.builder()
                .vcpus(1)
                .build();

        Assertions.assertEquals(1, command.getVcpus());
    }

    @Test
    public void whenCommandHasRamThenReturnRam() {

        FlavorCreateCommand command = FlavorCreateCommand.builder()
                .ram(1)
                .build();

        Assertions.assertEquals(1, command.getRam());
    }

    @Test
    public void whenCommandHasDiskThenReturnDisk() {

        FlavorCreateCommand command = FlavorCreateCommand.builder()
                .disk(1)
                .build();

        Assertions.assertEquals(1, command.getDisk());
    }

    @Test
    public void whenCommandHasInboundBandwidthThenReturnInboundBandwidth() {

        FlavorCreateCommand command = FlavorCreateCommand.builder()
                .inboundBandwidth(1)
                .build();

        Assertions.assertEquals(1, command.getInboundBandwidth());
    }

    @Test
    public void whenCommandHasOutboundBandwidthThenReturnOutboundBandwidth() {

        FlavorCreateCommand command = FlavorCreateCommand.builder()
                .outboundBandwidth(1)
                .build();

        Assertions.assertEquals(1, command.getOutboundBandwidth());
    }

    @Test
    public void whenCommandToString() {

        FlavorCreateCommand command = command();

        String expected = "FlavorCreateCommand(id=d81fa8e6-5666-44c2-b1d2-0f4300ae1bbc, regionId=regionId, ovhId=ovhId, name=name, type=type, available=true, hourly=hourly, monthly=monthly, quota=1, osType=osType, vcpus=1, ram=1, disk=1, inboundBandwidth=1, outboundBandwidth=1)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        FlavorCreateCommand command = command();

        Assertions.assertEquals(-1478794549, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        FlavorCreateCommand command1 = command();
        FlavorCreateCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        FlavorCreateCommand command = command();

        Assertions.assertNotEquals(command, FlavorCreateCommand.builder().build());
    }

    private FlavorCreateCommand command() {

        return FlavorCreateCommand.builder()
                .id(UUID.fromString("d81fa8e6-5666-44c2-b1d2-0f4300ae1bbc"))
                .regionId("regionId")
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
