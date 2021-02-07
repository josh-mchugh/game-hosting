package com.example.demo.game.aggregate.event;

import com.example.demo.game.entity.GameServerStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class GameServerCreatedEventTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        GameServerCreatedEvent model = GameServerCreatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasGameIdThenReturnGameId() {

        UUID gameId = UUID.randomUUID();

        GameServerCreatedEvent model = GameServerCreatedEvent.builder()
                .gameId(gameId)
                .build();

        Assertions.assertEquals(gameId, model.getGameId());
    }

    @Test
    public void whenModelHasRegionIdThenReturnRegionId() {

        UUID regionId = UUID.randomUUID();

        GameServerCreatedEvent model = GameServerCreatedEvent.builder()
                .regionId(regionId)
                .build();

        Assertions.assertEquals(regionId, model.getRegionId());
    }

    @Test
    public void whenModelHasFlavorIdThenReturnFlavorId() {

        UUID flavorId = UUID.randomUUID();

        GameServerCreatedEvent model = GameServerCreatedEvent.builder()
                .flavorId(flavorId)
                .build();

        Assertions.assertEquals(flavorId, model.getFlavorId());
    }

    @Test
    public void whenModelHasImageIdThenReturnImageId() {

        UUID imageId = UUID.randomUUID();

        GameServerCreatedEvent model = GameServerCreatedEvent.builder()
                .imageId(imageId)
                .build();

        Assertions.assertEquals(imageId, model.getImageId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        GameServerCreatedEvent model = GameServerCreatedEvent.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        GameServerCreatedEvent model = GameServerCreatedEvent.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelHasStatusThenReturnStatus() {

        GameServerCreatedEvent event = GameServerCreatedEvent.builder()
                .status(GameServerStatus.ACTIVE)
                .build();

        Assertions.assertEquals(GameServerStatus.ACTIVE, event.getStatus());
    }

    @Test
    public void whenModelToString() {

        GameServerCreatedEvent model = model();

        String expected = "GameServerCreatedEvent(id=43bf0199-2b55-463f-97d4-1b0a1c3e883d, gameId=03109481-9808-4982-8857-839f6e3357a3, regionId=7a3fb601-a36e-46b9-ae96-3be4e1205534, flavorId=70abc11e-07bc-4f18-a934-f5c737911179, imageId=37f8a2c3-16e1-4565-ba41-9c37706ec844, name=name, description=description, status=ACTIVE)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        GameServerCreatedEvent model = GameServerCreatedEvent.builder()
                .id(UUID.fromString("43bf0199-2b55-463f-97d4-1b0a1c3e883d"))
                .gameId(UUID.fromString("03109481-9808-4982-8857-839f6e3357a3"))
                .regionId(UUID.fromString("7a3fb601-a36e-46b9-ae96-3be4e1205534"))
                .flavorId(UUID.fromString("70abc11e-07bc-4f18-a934-f5c737911179"))
                .imageId(UUID.fromString("37f8a2c3-16e1-4565-ba41-9c37706ec844"))
                .name("name")
                .description("description")
                .build();

        Assertions.assertEquals(643607196, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        GameServerCreatedEvent model1 = model();
        GameServerCreatedEvent model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        GameServerCreatedEvent model = model();

        Assertions.assertNotEquals(model, GameServerCreatedEvent.builder().build());
    }

    private GameServerCreatedEvent model() {

        return GameServerCreatedEvent.builder()
                .id(UUID.fromString("43bf0199-2b55-463f-97d4-1b0a1c3e883d"))
                .gameId(UUID.fromString("03109481-9808-4982-8857-839f6e3357a3"))
                .regionId(UUID.fromString("7a3fb601-a36e-46b9-ae96-3be4e1205534"))
                .flavorId(UUID.fromString("70abc11e-07bc-4f18-a934-f5c737911179"))
                .imageId(UUID.fromString("37f8a2c3-16e1-4565-ba41-9c37706ec844"))
                .name("name")
                .description("description")
                .status(GameServerStatus.ACTIVE)
                .build();
    }
}
