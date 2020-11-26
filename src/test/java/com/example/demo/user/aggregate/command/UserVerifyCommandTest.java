package com.example.demo.user.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UserVerifyCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        UserVerifyCommand command = new UserVerifyCommand(id);

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandToString() {

        UserVerifyCommand command = command();

        String expected = "UserVerifyCommand(id=dc440670-18fc-4fa8-955c-23a4b35ceada)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        UserVerifyCommand command = command();

        Assertions.assertEquals(-491224863, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        UserVerifyCommand command1 = command();
        UserVerifyCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        UserVerifyCommand command = command();

        Assertions.assertNotEquals(command, new UserVerifyCommand(UUID.randomUUID()));
    }

    private UserVerifyCommand command() {

        return new UserVerifyCommand(UUID.fromString("dc440670-18fc-4fa8-955c-23a4b35ceada"));
    }
}
