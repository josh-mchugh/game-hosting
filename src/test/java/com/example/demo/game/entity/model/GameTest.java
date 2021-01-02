package com.example.demo.game.entity.model;

import com.example.demo.game.entity.GameType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    public void whenGameHasIdThenReturnId() {

        Game game = Game.builder()
                .id("id")
                .build();

        Assertions.assertEquals("id", game.getId());
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

        Game game = Game.builder()
                .id("id")
                .type(GameType.MINECRAFT_JAVA)
                .build();

        String toString = "Game(id=id, type=MINECRAFT_JAVA)";

        Assertions.assertEquals(toString, game.toString());
    }

    @Test
    public void whenGameHashCode() {

        Game game = game();

        Assertions.assertEquals(201469, game.hashCode());
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
                .id("id")
                .build();
    }
}
