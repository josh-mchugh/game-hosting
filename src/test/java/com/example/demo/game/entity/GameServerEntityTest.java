package com.example.demo.game.entity;

import com.example.demo.game.entity.model.GameServer;
import com.example.demo.ovh.flavor.entity.FlavorEntity;
import com.example.demo.ovh.image.entity.ImageEntity;
import com.example.demo.ovh.region.entity.RegionEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class GameServerEntityTest {

    @Test
    public void whenEntityHasIdThenReturnId() {

        GameServerEntity entity = new GameServerEntity();
        entity.setId("id");

        Assertions.assertEquals("id", entity.getId());
    }

    @Test
    public void whenEntityHasIdThenReturnUUID() {

        UUID id = UUID.randomUUID();

        GameServerEntity entity = new GameServerEntity();
        entity.setId(id);

        Assertions.assertEquals(id, entity.getUUID());
    }

    @Test
    public void whenEntityHasUUIDThenReturnId() {

        UUID id = UUID.randomUUID();

        GameServerEntity entity = new GameServerEntity();
        entity.setId(id);

        Assertions.assertEquals(id.toString(), entity.getId());
    }

    @Test
    public void whenEntityHasCreatedByThenReturnCreatedBy() {

        GameServerEntity entity = new GameServerEntity();
        entity.setCreatedBy("created-by");

        Assertions.assertEquals("created-by", entity.getCreatedBy());
    }

    @Test
    public void whenEntityHasCreatedDateThenReturnCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        GameServerEntity entity = new GameServerEntity();
        entity.setCreatedDate(createdDate);

        Assertions.assertEquals(createdDate, entity.getCreatedDate());
    }

    @Test
    public void whenEntityHasLastModifiedByThenReturnLastModifiedBy() {

        GameServerEntity entity = new GameServerEntity();
        entity.setLastModifiedBy("last-modified-by");

        Assertions.assertEquals("last-modified-by", entity.getLastModifiedBy());
    }

    @Test
    public void whenEntityHasLastModifiedDateThenReturnLastModifiedDate() {

        LocalDateTime lastModifiedDate = LocalDateTime.now();

        GameServerEntity entity = new GameServerEntity();
        entity.setLastModifiedDate(lastModifiedDate);

        Assertions.assertEquals(lastModifiedDate, entity.getLastModifiedDate());
    }

    @Test
    public void whenEntityHasVersionThenReturnVersion() {

        GameServerEntity entity = new GameServerEntity();
        entity.setVersion(1L);

        Assertions.assertEquals(1L, entity.getVersion());
    }

    @Test
    public void whenEntityHasGameEntityThenReturnGameEntity() {

        GameEntity gameEntity = new GameEntity();

        GameServerEntity entity = new GameServerEntity();
        entity.setGameEntity(gameEntity);

        Assertions.assertEquals(gameEntity, entity.getGameEntity());
    }

    @Test
    public void whenEntityHasRegionEntityThenReturnRegionEntity() {

        RegionEntity regionEntity = new RegionEntity();

        GameServerEntity entity = new GameServerEntity();
        entity.setRegionEntity(regionEntity);

        Assertions.assertEquals(regionEntity, entity.getRegionEntity());
    }

    @Test
    public void whenEntityHasFlavorEntityThenReturnFlavorEntity() {

        FlavorEntity flavorEntity = new FlavorEntity();

        GameServerEntity entity = new GameServerEntity();
        entity.setFlavorEntity(flavorEntity);

        Assertions.assertEquals(flavorEntity, entity.getFlavorEntity());
    }

    @Test
    public void whenEntityHasImageEntityThenReturnImageEntity() {

        ImageEntity imageEntity = new ImageEntity();

        GameServerEntity gameServerEntity = new GameServerEntity();
        gameServerEntity.setImageEntity(imageEntity);

        Assertions.assertEquals(imageEntity, gameServerEntity.getImageEntity());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        GameServerEntity entity = new GameServerEntity();
        entity.setName("name");

        Assertions.assertEquals("name", entity.getName());
    }

    @Test
    public void whenEntityHasDescriptionThenReturnDescription() {

        GameServerEntity entity = new GameServerEntity();
        entity.setDescription("description");

        Assertions.assertEquals("description", entity.getDescription());
    }
}
