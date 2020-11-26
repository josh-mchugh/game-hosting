package com.example.demo.user.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UserAuthFailCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        UserAuthFailCommand command = new UserAuthFailCommand(id);

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandToString() {

        UserAuthFailCommand command = command();

        String expected = "UserAuthFailCommand(id=72ef6e7b-73dd-44a6-a683-8866fe3987f1)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        UserAuthFailCommand command = command();

        Assertions.assertEquals(1502094725, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        UserAuthFailCommand command1 = command();
        UserAuthFailCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        UserAuthFailCommand command = command();

        Assertions.assertNotEquals(command, new UserAuthFailCommand(UUID.randomUUID()));
    }

    private UserAuthFailCommand command() {

        return new UserAuthFailCommand(UUID.fromString("72ef6e7b-73dd-44a6-a683-8866fe3987f1"));
    }
}
