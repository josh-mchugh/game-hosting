package com.example.demo.web.admin.game.service.projection;

import com.example.demo.game.entity.GameServerStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class GameServerCreateRequestTest {

    @Test
    public void whenModelHasNameThenReturnName() {

        GameServerCreateRequest model = GameServerCreateRequest.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        GameServerCreateRequest model = GameServerCreateRequest.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelHasStatusThenReturnStatus() {

        GameServerCreateRequest model = GameServerCreateRequest.builder()
                .status(GameServerStatus.ACTIVE)
                .build();

        Assertions.assertEquals(GameServerStatus.ACTIVE, model.getStatus());
    }

    @Test
    public void whenModelHasGameIdThenReturnGameId() {

        UUID gameId = UUID.randomUUID();

        GameServerCreateRequest model = GameServerCreateRequest.builder()
                .gameId(gameId)
                .build();

        Assertions.assertEquals(gameId, model.getGameId());
    }

    @Test
    public void whenModelHasRegionIdThenReturnRegionId() {

        UUID regionId = UUID.randomUUID();

        GameServerCreateRequest model = GameServerCreateRequest.builder()
                .regionId(regionId)
                .build();

        Assertions.assertEquals(regionId, model.getRegionId());
    }

    @Test
    public void whenModelHasFlavorIdThenReturnFlavorId() {

        UUID flavorId = UUID.randomUUID();

        GameServerCreateRequest model = GameServerCreateRequest.builder()
                .flavorId(flavorId)
                .build();

        Assertions.assertEquals(flavorId, model.getFlavorId());
    }

    @Test
    public void whenModelHasImageIdThenReturnImageId() {

        UUID imageId = UUID.randomUUID();

        GameServerCreateRequest model = GameServerCreateRequest.builder()
                .imageId(imageId)
                .build();

        Assertions.assertEquals(imageId, model.getImageId());
    }

    @Test
    public void whenModelToString() {

        GameServerCreateRequest model = model();

        String expected = "GameServerCreateRequest(name=name, description=description, status=ACTIVE, gameId=342f81ad-b4d2-4c52-a04c-65f075d80086, regionId=e5f0b664-a49b-47df-a744-d5ae64e457e4, flavorId=acbc6a5d-e971-43ae-9baf-bbadb21adaea, imageId=7b7973e7-caa5-4fff-9185-4c977394d4fa)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode(){

        GameServerCreateRequest model = GameServerCreateRequest.builder()
                .name("name")
                .description("description")
                .gameId(UUID.fromString("342f81ad-b4d2-4c52-a04c-65f075d80086"))
                .regionId(UUID.fromString("e5f0b664-a49b-47df-a744-d5ae64e457e4"))
                .flavorId(UUID.fromString("acbc6a5d-e971-43ae-9baf-bbadb21adaea"))
                .imageId(UUID.fromString("7b7973e7-caa5-4fff-9185-4c977394d4fa"))
                .build();

        Assertions.assertEquals(1615171482, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        GameServerCreateRequest model1 = model();
        GameServerCreateRequest model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        GameServerCreateRequest model = model();

        Assertions.assertNotEquals(model, GameServerCreateRequest.builder().build());
    }

    private GameServerCreateRequest model() {

        return GameServerCreateRequest.builder()
                .name("name")
                .description("description")
                .status(GameServerStatus.ACTIVE)
                .gameId(UUID.fromString("342f81ad-b4d2-4c52-a04c-65f075d80086"))
                .regionId(UUID.fromString("e5f0b664-a49b-47df-a744-d5ae64e457e4"))
                .flavorId(UUID.fromString("acbc6a5d-e971-43ae-9baf-bbadb21adaea"))
                .imageId(UUID.fromString("7b7973e7-caa5-4fff-9185-4c977394d4fa"))
                .build();
    }
}
