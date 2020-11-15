package com.example.demo.email.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class EmailFailCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        EmailFailCommand command = new EmailFailCommand(id);

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandToString() {

        EmailFailCommand command = command();

        String toString = "EmailFailCommand(id=4b24c34b-41f8-4f21-a9fc-99980872ab1e)";

        Assertions.assertEquals(toString, command.toString());
    }

    @Test
    public void whenCommandHasCode() {

        EmailFailCommand command = command();

        Assertions.assertEquals(-1420640473, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        EmailFailCommand command1 = command();
        EmailFailCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        EmailFailCommand command = command();

        Assertions.assertNotEquals(command, new EmailFailCommand(UUID.randomUUID()));
    }

    private EmailFailCommand command() {

        return new EmailFailCommand(UUID.fromString("4b24c34b-41f8-4f21-a9fc-99980872ab1e"));
    }
}
