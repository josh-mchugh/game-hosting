package com.example.demo.game.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class GameEntityTest {

    @Test
    public void whenEntityHasIdThenReturnId() {

        GameEntity entity = new GameEntity();
        entity.setId("id");

        Assertions.assertEquals("id", entity.getId());
    }

    @Test
    public void whenEntityHasUUIDThenReturnId() {

        UUID id = UUID.randomUUID();

        GameEntity entity = new GameEntity();
        entity.setId(id);

        Assertions.assertEquals(id.toString(), entity.getId());
    }

    @Test
    public void whenEntityHasCreatedByThenReturnCreatedBy() {

        GameEntity entity = new GameEntity();
        entity.setCreatedBy("created-by");

        Assertions.assertEquals("created-by", entity.getCreatedBy());
    }

    @Test
    public void whenEntityHasCreatedDateThenReturnCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        GameEntity entity = new GameEntity();
        entity.setCreatedDate(createdDate);

        Assertions.assertEquals(createdDate, entity.getCreatedDate());
    }

    @Test
    public void whenEntityHasLastModifiedByThenReturnLastModifiedBy() {

        GameEntity entity = new GameEntity();
        entity.setLastModifiedBy("last-modified-by");

        Assertions.assertEquals("last-modified-by", entity.getLastModifiedBy());
    }

    @Test
    public void whenEntityHasLastModifiedDateThenReturnLastModifiedDate() {

        LocalDateTime lastModifiedDate = LocalDateTime.now();

        GameEntity entity = new GameEntity();
        entity.setLastModifiedDate(lastModifiedDate);

        Assertions.assertEquals(lastModifiedDate, entity.getLastModifiedDate());
    }

    @Test
    public void whenEntityHasVersionThenReturnVersion() {

        GameEntity entity = new GameEntity();
        entity.setVersion(1L);

        Assertions.assertEquals(1L, entity.getVersion());
    }

    @Test
    public void whenEntityHasTypeThenReturnType() {

        GameEntity entity = new GameEntity();
        entity.setType(GameType.MINECRAFT_JAVA);

        Assertions.assertEquals(GameType.MINECRAFT_JAVA, entity.getType());
    }
}
