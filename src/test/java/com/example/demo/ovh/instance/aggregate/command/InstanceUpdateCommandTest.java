package com.example.demo.ovh.instance.aggregate.command;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class InstanceUpdateCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        InstanceUpdateCommand command = InstanceUpdateCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasNameThenReturnName() {

        InstanceUpdateCommand command = InstanceUpdateCommand.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", command.getName());
    }

    @Test
    public void whenCommandHasStatusThenReturnStatus() {

        InstanceUpdateCommand command = InstanceUpdateCommand.builder()
                .status(InstanceStatus.ACTIVE)
                .build();

        Assertions.assertEquals(InstanceStatus.ACTIVE, command.getStatus());
    }

    @Test
    public void whenCommandHasIp4AddressThenReturnIp4Address() {

        InstanceUpdateCommand command = InstanceUpdateCommand.builder()
                .ip4Address("ip4Address")
                .build();

        Assertions.assertEquals("ip4Address", command.getIp4Address());
    }

    @Test
    public void whenCommandHasIp6AddressThenReturnIp6Address() {

        InstanceUpdateCommand command = InstanceUpdateCommand.builder()
                .ip6Address("ip6Address")
                .build();

        Assertions.assertEquals("ip6Address", command.getIp6Address());
    }

    @Test
    public void whenCommandHasInstanceCreatedDateThenReturnInstanceCreatedDate() {

        LocalDateTime instanceCreatedDate = LocalDateTime.now();

        InstanceUpdateCommand command = InstanceUpdateCommand.builder()
                .instanceCreatedDate(instanceCreatedDate)
                .build();

        Assertions.assertEquals(instanceCreatedDate, command.getInstanceCreatedDate());
    }

    @Test
    public void whenCommandToString() {

        InstanceUpdateCommand command = InstanceUpdateCommand.builder()
                .id(UUID.fromString("efbe3bf6-9ffe-4411-8b2c-e133461bf42f"))
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .ip4Address("ip4Address")
                .ip6Address("ip6Address")
                .instanceCreatedDate(LocalDateTime.of(2020, 11, 28, 22, 10))
                .build();

        String expected = "InstanceUpdateCommand(id=efbe3bf6-9ffe-4411-8b2c-e133461bf42f, name=name, status=ACTIVE, ip4Address=ip4Address, ip6Address=ip6Address, instanceCreatedDate=2020-11-28T22:10)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        InstanceUpdateCommand command = command();

        Assertions.assertEquals(1699408851, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        InstanceUpdateCommand command1 = command();
        InstanceUpdateCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        InstanceUpdateCommand command = command();

        Assertions.assertNotEquals(command, InstanceUpdateCommand.builder().build());
    }

    private InstanceUpdateCommand command() {

        return InstanceUpdateCommand.builder()
                .id(UUID.fromString("efbe3bf6-9ffe-4411-8b2c-e133461bf42f"))
                .name("name")
                .ip4Address("ip4Address")
                .ip6Address("ip6Address")
                .instanceCreatedDate(LocalDateTime.of(2020, 11, 28, 22, 10))
                .build();
    }
}
