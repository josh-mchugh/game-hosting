package com.example.demo.game.aggregate.command;

import com.example.demo.game.entity.GameType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class GameCreateCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        GameCreateCommand command = GameCreateCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasTypeThenReturnType() {

        GameCreateCommand command = GameCreateCommand.builder()
                .type(GameType.MINECRAFT_JAVA)
                .build();

        Assertions.assertEquals(GameType.MINECRAFT_JAVA, command.getType());
    }

    @Test
    public void whenCommandToString() {

        GameCreateCommand command = GameCreateCommand.builder()
                .id(UUID.fromString("db99f245-805f-47de-b90d-827e91da7956"))
                .type(GameType.MINECRAFT_JAVA)
                .build();

        String toString = "GameCreateCommand(id=db99f245-805f-47de-b90d-827e91da7956, type=MINECRAFT_JAVA)";

        Assertions.assertEquals(toString, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        GameCreateCommand command = command();

        Assertions.assertEquals(-2063781627, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        GameCreateCommand command1 = command();
        GameCreateCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        GameCreateCommand command = command();

        Assertions.assertNotEquals(command, GameCreateCommand.builder().build());
    }

    private GameCreateCommand command() {

        return GameCreateCommand.builder()
                .id(UUID.fromString("db99f245-805f-47de-b90d-827e91da7956"))
                .build();
    }

}
