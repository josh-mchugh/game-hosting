package com.example.demo.user.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UserCreateRegularCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        UserCreateRegularCommand command = UserCreateRegularCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasEmailThenReturnEmail() {

        UserCreateRegularCommand command = UserCreateRegularCommand.builder()
                .email("test@test")
                .build();

        Assertions.assertEquals("test@test", command.getEmail());
    }

    @Test
    public void whenCommandHasPasswordThenReturnPassword() {

        UserCreateRegularCommand command = UserCreateRegularCommand.builder()
                .password("password")
                .build();

        Assertions.assertEquals("password", command.getPassword());
    }

    @Test
    public void whenCommandToString() {

        UserCreateRegularCommand command = command();

        String expected = "UserCreateRegularCommand(id=387300fc-517d-4b37-b31f-2850e541336b, email=test@test, password=password)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        UserCreateRegularCommand command = command();

        Assertions.assertEquals(-1730104626, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        UserCreateRegularCommand command1 = command();
        UserCreateRegularCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        UserCreateRegularCommand command = command();

        Assertions.assertNotEquals(command, UserCreateRegularCommand.builder().build());
    }

    private UserCreateRegularCommand command() {

        return UserCreateRegularCommand.builder()
                .id(UUID.fromString("387300fc-517d-4b37-b31f-2850e541336b"))
                .email("test@test")
                .password("password")
                .build();
    }
}
