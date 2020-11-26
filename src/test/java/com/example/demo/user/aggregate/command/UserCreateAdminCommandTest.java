package com.example.demo.user.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UserCreateAdminCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        UserCreateAdminCommand command = UserCreateAdminCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasEmailThenReturnEmail() {

        UserCreateAdminCommand command = UserCreateAdminCommand.builder()
                .email("test@test")
                .build();

        Assertions.assertEquals("test@test", command.getEmail());
    }

    @Test
    public void whenCommandHasPasswordThenReturnPassword() {

        UserCreateAdminCommand command = UserCreateAdminCommand.builder()
                .password("password")
                .build();

        Assertions.assertEquals("password", command.getPassword());
    }

    @Test
    public void whenCommandToString() {

        UserCreateAdminCommand command = command();

        String expected = "UserCreateAdminCommand(id=387300fc-517d-4b37-b31f-2850e541336b, email=test@test, password=password)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        UserCreateAdminCommand command = command();

        Assertions.assertEquals(-1730104626, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        UserCreateAdminCommand command1 = command();
        UserCreateAdminCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        UserCreateAdminCommand command = command();

        Assertions.assertNotEquals(command, UserCreateAdminCommand.builder().build());
    }

    private UserCreateAdminCommand command() {

        return UserCreateAdminCommand.builder()
                .id(UUID.fromString("387300fc-517d-4b37-b31f-2850e541336b"))
                .email("test@test")
                .password("password")
                .build();
    }
}
