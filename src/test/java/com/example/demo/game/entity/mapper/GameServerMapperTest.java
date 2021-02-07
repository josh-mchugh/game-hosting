package com.example.demo.game.entity.mapper;

import com.example.demo.game.entity.GameServerEntity;
import com.example.demo.game.entity.GameServerStatus;
import com.example.demo.game.entity.model.GameServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class GameServerMapperTest {

    @Test
    public void whenEntityIsNullThenExpectNull() {

        Assertions.assertNull(GameServerMapper.map(null));
    }

    @Test
    public void whenEntityHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        GameServerEntity entity = new GameServerEntity();
        entity.setId(id);

        GameServer model = GameServerMapper.map(entity);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        GameServerEntity entity = new GameServerEntity();
        entity.setName("name");

        GameServer model = GameServerMapper.map(entity);

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenEntityHasDescriptionThenReturnDescription() {

        GameServerEntity entity = new GameServerEntity();
        entity.setDescription("description");

        GameServer model = GameServerMapper.map(entity);

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenEntityHasStatusThenReturnStatus() {

        GameServerEntity entity = new GameServerEntity();
        entity.setStatus(GameServerStatus.ACTIVE);

        GameServer model = GameServerMapper.map(entity);

        Assertions.assertEquals(GameServerStatus.ACTIVE, model.getStatus());
    }
}
