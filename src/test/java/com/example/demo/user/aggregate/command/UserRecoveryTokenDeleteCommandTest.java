package com.example.demo.user.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UserRecoveryTokenDeleteCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        UserRecoveryTokenDeleteCommand command = new UserRecoveryTokenDeleteCommand(id);

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandToString() {

        UserRecoveryTokenDeleteCommand command = command();

        String expected = "UserRecoveryTokenDeleteCommand(id=5612b094-4419-441d-9c86-fc8cd19efbc4)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        UserRecoveryTokenDeleteCommand command = command();

        Assertions.assertEquals(1595143164, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        UserRecoveryTokenDeleteCommand command1 = command();
        UserRecoveryTokenDeleteCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        UserRecoveryTokenDeleteCommand command = command();

        Assertions.assertNotEquals(command, new UserRecoveryTokenDeleteCommand(UUID.randomUUID()));
    }

    private UserRecoveryTokenDeleteCommand command() {

        return new UserRecoveryTokenDeleteCommand(UUID.fromString("5612b094-4419-441d-9c86-fc8cd19efbc4"));
    }
}
