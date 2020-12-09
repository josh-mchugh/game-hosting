package com.example.demo.awx.host.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxHostDisableCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxHostDisableCommand command = new AwxHostDisableCommand(id);

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandToString() {

        AwxHostDisableCommand command = command();

        String expected = "AwxHostDisableCommand(id=08c6785c-3638-499d-876e-901d8e7faf7c)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        AwxHostDisableCommand command = command();

        Assertions.assertEquals(938413787, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        AwxHostDisableCommand command1 = command();
        AwxHostDisableCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        AwxHostDisableCommand command = command();

        Assertions.assertNotEquals(command, new AwxHostDisableCommand(UUID.randomUUID()));
    }

    private AwxHostDisableCommand command() {

        return new AwxHostDisableCommand(UUID.fromString("08c6785c-3638-499d-876e-901d8e7faf7c"));
    }
}
