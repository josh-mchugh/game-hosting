package com.example.demo.ovh.flavor.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FlavorUpdateCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FlavorUpdateCommand command = FlavorUpdateCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasNameThenReturnName() {

        FlavorUpdateCommand command = FlavorUpdateCommand.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", command.getName());
    }

    @Test
    public void whenCommandHasTypeThenReturnType() {

        FlavorUpdateCommand command = FlavorUpdateCommand.builder()
                .type("type")
                .build();

        Assertions.assertEquals("type", command.getType());
    }

    @Test
    public void whenCommandHasAvailableThenReturnAvailable() {

        FlavorUpdateCommand command = FlavorUpdateCommand.builder()
                .available(true)
                .build();

        Assertions.assertTrue(command.getAvailable());
    }

    @Test
    public void whenCommandHasHourlyThenReturnHourly() {

        FlavorUpdateCommand command = FlavorUpdateCommand.builder()
                .hourly("hourly")
                .build();

        Assertions.assertEquals("hourly", command.getHourly());
    }

    @Test
    public void whenCommandHasMonthlyThenReturnMonthly() {

        FlavorUpdateCommand command = FlavorUpdateCommand.builder()
                .monthly("monthly")
                .build();

        Assertions.assertEquals("monthly", command.getMonthly());
    }

    @Test
    public void whenCommandHasQuotaThenReturnQuota() {

        FlavorUpdateCommand command = FlavorUpdateCommand.builder()
                .quota(1)
                .build();

        Assertions.assertEquals(1, command.getQuota());
    }

    @Test
    public void whenCommandHasOsTypeThenReturnOsType() {

        FlavorUpdateCommand command = FlavorUpdateCommand.builder()
                .osType("osType")
                .build();

        Assertions.assertEquals("osType", command.getOsType());
    }

    @Test
    public void whenCommandHasVcpusThenReturnVcpus() {

        FlavorUpdateCommand command = FlavorUpdateCommand.builder()
                .vcpus(1)
                .build();

        Assertions.assertEquals(1, command.getVcpus());
    }

    @Test
    public void whenCommandHasRamThenReturnRam() {

        FlavorUpdateCommand command = FlavorUpdateCommand.builder()
                .ram(1)
                .build();

        Assertions.assertEquals(1, command.getRam());
    }

    @Test
    public void whenCommandHasDiskThenReturnDisk() {

        FlavorUpdateCommand command = FlavorUpdateCommand.builder()
                .disk(1)
                .build();

        Assertions.assertEquals(1, command.getDisk());
    }

    @Test
    public void whenCommandHasInboundBandwidthThenReturnInboundBandwidth() {

        FlavorUpdateCommand command = FlavorUpdateCommand.builder()
                .inboundBandwidth(1)
                .build();

        Assertions.assertEquals(1, command.getInboundBandwidth());
    }

    @Test
    public void whenCommandHasOutboundBandwidthThenReturnOutboundBandwidth() {

        FlavorUpdateCommand command = FlavorUpdateCommand.builder()
                .outboundBandwidth(1)
                .build();

        Assertions.assertEquals(1, command.getOutboundBandwidth());
    }

    @Test
    public void whenCommandToString() {

        FlavorUpdateCommand command = command();

        String expected = "FlavorUpdateCommand(id=d81fa8e6-5666-44c2-b1d2-0f4300ae1bbc, name=name, type=type, available=true, hourly=hourly, monthly=monthly, quota=1, osType=osType, vcpus=1, ram=1, disk=1, inboundBandwidth=1, outboundBandwidth=1)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        FlavorUpdateCommand command = command();

        Assertions.assertEquals(-2019563462, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        FlavorUpdateCommand command1 = command();
        FlavorUpdateCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        FlavorUpdateCommand command = command();

        Assertions.assertNotEquals(command, FlavorUpdateCommand.builder().build());
    }

    private FlavorUpdateCommand command() {

        return FlavorUpdateCommand.builder()
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
