package com.example.demo.user.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UserAuthSuccessCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        UserAuthSuccessCommand command = new UserAuthSuccessCommand(id);

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandToString() {

        UserAuthSuccessCommand command = command();

        String expected = "UserAuthSuccessCommand(id=d3ddc403-9ab5-494c-84b9-ac559c655a68)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        UserAuthSuccessCommand command = command();

        Assertions.assertEquals(1370782637, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        UserAuthSuccessCommand command1 = command();
        UserAuthSuccessCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        UserAuthSuccessCommand command = command();

        Assertions.assertNotEquals(command, new UserAuthSuccessCommand(UUID.randomUUID()));
    }

    private UserAuthSuccessCommand command() {

        return new UserAuthSuccessCommand(UUID.fromString("d3ddc403-9ab5-494c-84b9-ac559c655a68"));
    }
}
