package com.example.demo.game.entity.model;

import com.example.demo.game.entity.GameServerStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class GameServerTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        GameServer model = GameServer.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        GameServer model = GameServer.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        GameServer model = GameServer.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelHasStatusThenReturnStatus() {

        GameServer model = GameServer.builder()
                .status(GameServerStatus.ACTIVE)
                .build();

        Assertions.assertEquals(GameServerStatus.ACTIVE, model.getStatus());
    }

    @Test
    public void whenModelToString() {

        GameServer model = model();

        String expected = "GameServer(id=7770aa9c-bbdc-4193-b57f-3126386a226b, name=name, description=description, status=ACTIVE)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        GameServer model = GameServer.builder()
                .id(UUID.fromString("7770aa9c-bbdc-4193-b57f-3126386a226b"))
                .name("name")
                .description("description")
                .build();

        Assertions.assertEquals(-613000695, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        GameServer model1 = model();
        GameServer model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        GameServer model = model();

        Assertions.assertNotEquals(model, GameServer.builder().build());
    }

    private GameServer model() {

        return GameServer.builder()
                .id(UUID.fromString("7770aa9c-bbdc-4193-b57f-3126386a226b"))
                .name("name")
                .description("description")
                .status(GameServerStatus.ACTIVE)
                .build();
    }
}
