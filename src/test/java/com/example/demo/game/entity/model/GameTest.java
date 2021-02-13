package com.example.demo.game.entity.model;

import com.example.demo.game.entity.GameType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class GameTest {

    @Test
    public void whenGameHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        Game game = Game.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, game.getId());
    }

    @Test
    public void whenGameHasTypeThenReturnType() {

        Game game = Game.builder()
                .type(GameType.MINECRAFT_JAVA)
                .build();

        Assertions.assertEquals(GameType.MINECRAFT_JAVA, game.getType());
    }

    @Test
    public void whenGameToString() {

        Game game = game();

        String toString = "Game(id=e85abb3c-69ba-4c52-98c9-7df63b9bc2ed, type=MINECRAFT_JAVA)";

        Assertions.assertEquals(toString, game.toString());
    }

    @Test
    public void whenGameHashCode() {

        Game game = Game.builder()
                .id(UUID.fromString("e85abb3c-69ba-4c52-98c9-7df63b9bc2ed"))
                .build();

        Assertions.assertEquals(-15286085, game.hashCode());
    }

    @Test
    public void whenGameEquals() {

        Game game1 = game();
        Game game2 = game();

        Assertions.assertEquals(game1, game2);
    }

    @Test
    public void whenGameNotEquals() {

        Game game = game();

        Assertions.assertNotEquals(game, Game.builder().build());
    }

    private Game game() {

        return Game.builder()
                .id(UUID.fromString("e85abb3c-69ba-4c52-98c9-7df63b9bc2ed"))
                .type(GameType.MINECRAFT_JAVA)
                .build();
    }
}
