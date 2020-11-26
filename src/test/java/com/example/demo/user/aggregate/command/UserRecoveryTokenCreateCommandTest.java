package com.example.demo.user.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UserRecoveryTokenCreateCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        UserRecoveryTokenCreateCommand command = new UserRecoveryTokenCreateCommand(id);

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandToString() {

        UserRecoveryTokenCreateCommand command = command();

        String expected = "UserRecoveryTokenCreateCommand(id=ac553a9e-16f1-45f5-a88f-99970226debb)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        UserRecoveryTokenCreateCommand command = command();

        Assertions.assertEquals(269301890, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        UserRecoveryTokenCreateCommand command1 = command();
        UserRecoveryTokenCreateCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        UserRecoveryTokenCreateCommand command = command();

        Assertions.assertNotEquals(command, new UserRecoveryTokenCreateCommand(UUID.randomUUID()));
    }

    private UserRecoveryTokenCreateCommand command() {

        return new UserRecoveryTokenCreateCommand(UUID.fromString("ac553a9e-16f1-45f5-a88f-99970226debb"));
    }
}

