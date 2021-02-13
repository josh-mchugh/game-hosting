package com.example.demo.project.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectCreateCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectCreateCommand command = ProjectCreateCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasNameThenReturnName() {

        ProjectCreateCommand command = ProjectCreateCommand.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", command.getName());
    }

    @Test
    public void whenCommandHasUserIdThenReturnUserId() {

        ProjectCreateCommand command = ProjectCreateCommand.builder()
                .userId("userId")
                .build();

        Assertions.assertEquals("userId", command.getUserId());
    }

    @Test
    public void whenCommandHasGameIdThenReturnGameId() {

        UUID gameId = UUID.randomUUID();

        ProjectCreateCommand command = ProjectCreateCommand.builder()
                .gameId(gameId)
                .build();

        Assertions.assertEquals(gameId, command.getGameId());
    }

    @Test
    public void whenCommandToString() {

        ProjectCreateCommand command = command();

        String expected = "ProjectCreateCommand(id=7a234444-3906-49ff-a9aa-b900644d3ac5, name=name, userId=userId, gameId=7d8485e0-a89b-46b0-95b6-7e6ae72eaa32)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        ProjectCreateCommand command = command();

        Assertions.assertEquals(-947717720, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        ProjectCreateCommand command1 = command();
        ProjectCreateCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        ProjectCreateCommand command = command();

        Assertions.assertNotEquals(command, ProjectCreateCommand.builder().build());
    }

    private ProjectCreateCommand command() {

        return ProjectCreateCommand.builder()
                .id(UUID.fromString("7a234444-3906-49ff-a9aa-b900644d3ac5"))
                .gameId(UUID.fromString("7d8485e0-a89b-46b0-95b6-7e6ae72eaa32"))
                .name("name")
                .userId("userId")
                .build();
    }
}
