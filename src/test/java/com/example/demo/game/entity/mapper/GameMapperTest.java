package com.example.demo.game.entity.mapper;

import com.example.demo.game.entity.GameEntity;
import com.example.demo.game.entity.GameType;
import com.example.demo.game.entity.mapper.GameMapper;
import com.example.demo.game.entity.model.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class GameMapperTest {

    @Test
    public void whenEntityIsNullReturnNull() {

        Assertions.assertNull(GameMapper.map(null));
    }

    @Test
    public void whenEntityIsNotNullReturnGame() {

        Assertions.assertNotNull(GameMapper.map(new GameEntity()));
    }

    @Test
    public void whenEntityHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(id);

        Game game = GameMapper.map(gameEntity);

        Assertions.assertEquals(id, game.getId());
    }

    @Test
    public void whenEntityHasNullIdThenReturnNull() {

        Game game = GameMapper.map(new GameEntity());

        Assertions.assertNull(game.getId());
    }

    @Test
    public void whenEntityHasTypeThenReturnType() {

        GameEntity gameEntity = new GameEntity();
        gameEntity.setType(GameType.MINECRAFT_JAVA);

        Game game = GameMapper.map(gameEntity);

        Assertions.assertEquals(GameType.MINECRAFT_JAVA, game.getType());
    }

    @Test
    public void whenEntityHasNullTypeThenReturnNull() {

        Game game = GameMapper.map(new GameEntity());

        Assertions.assertNull(game.getType());
    }
}
