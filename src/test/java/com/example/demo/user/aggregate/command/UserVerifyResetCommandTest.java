package com.example.demo.user.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UserVerifyResetCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        UserVerifyResetCommand command = new UserVerifyResetCommand(id);

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandToString() {

        UserVerifyResetCommand command = command();

        String expected = "UserVerifyResetCommand(id=6735988d-12ac-4dbd-a1df-bfe57cb99dda)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        UserVerifyResetCommand command = command();

        Assertions.assertEquals(-1459620022, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        UserVerifyResetCommand command1 = command();
        UserVerifyResetCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        UserVerifyResetCommand command = command();

        Assertions.assertNotEquals(command, new UserVerifyResetCommand(UUID.randomUUID()));
    }

    private UserVerifyResetCommand command() {

        return new UserVerifyResetCommand(UUID.fromString("6735988d-12ac-4dbd-a1df-bfe57cb99dda"));
    }
}
