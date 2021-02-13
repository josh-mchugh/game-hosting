package com.example.demo.awx.host.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxHostCreateCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxHostCreateCommand command = AwxHostCreateCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasAwxInventoryIdThenReturnAwxInventoryId() {

        UUID awxInventoryId = UUID.randomUUID();

        AwxHostCreateCommand command = AwxHostCreateCommand.builder()
                .awxInventoryId(awxInventoryId)
                .build();

        Assertions.assertEquals(awxInventoryId, command.getAwxInventoryId());
    }

    @Test
    public void whenCommandHasInstanceIdThenReturnInstanceId() {

        UUID instanceId = UUID.randomUUID();

        AwxHostCreateCommand command = AwxHostCreateCommand.builder()
                .instanceId(instanceId)
                .build();

        Assertions.assertEquals(instanceId, command.getInstanceId());
    }

    @Test
    public void whenCommandHasHostIdThenHostId() {

        AwxHostCreateCommand command = AwxHostCreateCommand.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, command.getAwxId());
    }

    @Test
    public void whenCommandHasHostNameThenReturnHostName() {

        AwxHostCreateCommand command = AwxHostCreateCommand.builder()
                .hostname("hostname")
                .build();

        Assertions.assertEquals("hostname", command.getHostname());
    }

    @Test
    public void whenCommandHasDescriptionThenReturnDescription() {

        AwxHostCreateCommand command = AwxHostCreateCommand.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", command.getDescription());
    }

    @Test
    public void whenCommandHasEnabledThenReturnEnabled() {

        AwxHostCreateCommand command = AwxHostCreateCommand.builder()
                .enabled(true)
                .build();

        Assertions.assertTrue(command.getEnabled());
    }

    @Test
    public void whenCommandToString() {

        AwxHostCreateCommand command = command();

        String expected = "AwxHostCreateCommand(id=ecf182f8-be26-49fe-b8d4-3e21b47417a4, awxInventoryId=7bfc6d33-8ed8-45e6-8b0c-56aa5130de8c, instanceId=9f6f134d-401c-4636-a817-324781a1e192, awxId=1, hostname=hostname, description=description, enabled=true)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        AwxHostCreateCommand command = command();

        Assertions.assertEquals(-803063922, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        AwxHostCreateCommand command1 = command();
        AwxHostCreateCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        AwxHostCreateCommand command = command();

        Assertions.assertNotEquals(command, AwxHostCreateCommand.builder().build());
    }

    private AwxHostCreateCommand command() {

        return  AwxHostCreateCommand.builder()
                .id(UUID.fromString("ecf182f8-be26-49fe-b8d4-3e21b47417a4"))
                .awxInventoryId(UUID.fromString("7bfc6d33-8ed8-45e6-8b0c-56aa5130de8c"))
                .instanceId(UUID.fromString("9f6f134d-401c-4636-a817-324781a1e192"))
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();
    }
}
