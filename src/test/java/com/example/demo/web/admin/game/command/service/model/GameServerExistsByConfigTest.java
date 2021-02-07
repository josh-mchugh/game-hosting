package com.example.demo.web.admin.game.command.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameServerExistsByConfigTest {

    @Test
    public void whenModelHasGameIdThenReturnGameId() {

        GameServerExistsByConfig model = GameServerExistsByConfig.builder()
                .gameId("gameId")
                .build();

        Assertions.assertEquals("gameId", model.getGameId());
    }

    @Test
    public void whenModelHasRegionIdThenReturnRegionId() {

        GameServerExistsByConfig model = GameServerExistsByConfig.builder()
                .regionId("regionId")
                .build();

        Assertions.assertEquals("regionId", model.getRegionId());
    }

    @Test
    public void whenModelHasFlavorIdThenReturnFlavorId() {

        GameServerExistsByConfig model = GameServerExistsByConfig.builder()
                .flavorId("flavorId")
                .build();

        Assertions.assertEquals("flavorId", model.getFlavorId());
    }

    @Test
    public void whenModelHasImageIdThenReturnImageId(){

        GameServerExistsByConfig model = GameServerExistsByConfig.builder()
                .imageId("imageId")
                .build();

        Assertions.assertEquals("imageId", model.getImageId());
    }

    @Test
    public void whenModelToString() {

        GameServerExistsByConfig model = model();

        String expected = "GameServerExistsByConfig(gameId=gameId, regionId=regionId, flavorId=flavorId, imageId=imageId)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        GameServerExistsByConfig model = model();

        Assertions.assertEquals(1664814888, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        GameServerExistsByConfig model1 = model();
        GameServerExistsByConfig model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        GameServerExistsByConfig model = model();

        Assertions.assertNotEquals(model, GameServerExistsByConfig.builder().build());
    }

    private GameServerExistsByConfig model() {

        return GameServerExistsByConfig.builder()
                .gameId("gameId")
                .regionId("regionId")
                .flavorId("flavorId")
                .imageId("imageId")
                .build();
    }
}
