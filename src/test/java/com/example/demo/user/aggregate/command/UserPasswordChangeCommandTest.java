package com.example.demo.user.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UserPasswordChangeCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        UserPasswordChangeCommand command = UserPasswordChangeCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasPasswordThenReturnPassword() {

        UserPasswordChangeCommand command = UserPasswordChangeCommand.builder()
                .password("password")
                .build();

        Assertions.assertEquals("password", command.getPassword());
    }

    @Test
    public void whenCommandToString() {

        UserPasswordChangeCommand command = command();

        String expected = "UserPasswordChangeCommand(id=ca7b0987-3e49-4ff4-a196-d373fc16cc69, password=password)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        UserPasswordChangeCommand command = command();

        Assertions.assertEquals(1688494951, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        UserPasswordChangeCommand command1 = command();
        UserPasswordChangeCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        UserPasswordChangeCommand command = command();

        Assertions.assertNotEquals(command, UserPasswordChangeCommand.builder().build());
    }

    private UserPasswordChangeCommand command() {

        return UserPasswordChangeCommand.builder()
                .id(UUID.fromString("ca7b0987-3e49-4ff4-a196-d373fc16cc69"))
                .password("password")
                .build();
    }
}
