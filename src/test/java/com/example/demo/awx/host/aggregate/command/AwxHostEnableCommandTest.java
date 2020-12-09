package com.example.demo.awx.host.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxHostEnableCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxHostEnableCommand command = new AwxHostEnableCommand(id);

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandToString() {

        AwxHostEnableCommand command = command();

        String expected = "AwxHostEnableCommand(id=08c6785c-3638-499d-876e-901d8e7faf7c)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        AwxHostEnableCommand command = command();

        Assertions.assertEquals(938413787, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        AwxHostEnableCommand command1 = command();
        AwxHostEnableCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        AwxHostEnableCommand command = command();

        Assertions.assertNotEquals(command, new AwxHostEnableCommand(UUID.randomUUID()));
    }

    private AwxHostEnableCommand command() {

        return new AwxHostEnableCommand(UUID.fromString("08c6785c-3638-499d-876e-901d8e7faf7c"));
    }
}
