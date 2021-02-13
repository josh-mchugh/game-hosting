package com.example.demo.game.entity.service;

import com.example.demo.game.aggregate.event.GameServerCreatedEvent;
import com.example.demo.game.entity.GameServerStatus;
import com.example.demo.game.entity.model.GameServer;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class GameServerServiceCreatedTest {

    @Autowired
    private IGameServerService gameServerService;

    @Autowired
    private SampleBuilder sampleBuilder;

    private SampleData data;

    @BeforeEach
    public void setup() {

        data = sampleBuilder.builder()
                .game()
                .region()
                .flavor()
                .image()
                .build();
    }

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        GameServerCreatedEvent event = GameServerCreatedEvent.builder()
                .id(id)
                .gameId(data.getGame().getId())
                .regionId(UUID.fromString(data.getRegion().getId()))
                .flavorId(data.getFlavor().getId())
                .imageId(UUID.fromString(data.getImage().getId()))
                .name("name")
                .status(GameServerStatus.ACTIVE)
                .build();

        GameServer gameServer = gameServerService.handleCreated(event);

        Assertions.assertEquals(id, gameServer.getId());
    }

    @Test
    public void whenEventHasNameThenReturnName() {

        GameServerCreatedEvent event = GameServerCreatedEvent.builder()
                .id(UUID.randomUUID())
                .gameId(data.getGame().getId())
                .regionId(UUID.fromString(data.getRegion().getId()))
                .flavorId(data.getFlavor().getId())
                .imageId(UUID.fromString(data.getImage().getId()))
                .name("name")
                .status(GameServerStatus.ACTIVE)
                .build();

        GameServer gameServer = gameServerService.handleCreated(event);

        Assertions.assertEquals("name", gameServer.getName());
    }

    @Test
    public void whenEventHasDescriptionThenReturnDescription() {

        GameServerCreatedEvent event = GameServerCreatedEvent.builder()
                .id(UUID.randomUUID())
                .gameId(data.getGame().getId())
                .regionId(UUID.fromString(data.getRegion().getId()))
                .flavorId(data.getFlavor().getId())
                .imageId(UUID.fromString(data.getImage().getId()))
                .name("name")
                .description("description")
                .status(GameServerStatus.ACTIVE)
                .build();

        GameServer gameServer = gameServerService.handleCreated(event);

        Assertions.assertEquals("description", gameServer.getDescription());
    }

    @Test
    public void whenEventHasStatusThenReturnStatus() {

        GameServerCreatedEvent event = GameServerCreatedEvent.builder()
                .id(UUID.randomUUID())
                .gameId(data.getGame().getId())
                .regionId(UUID.fromString(data.getRegion().getId()))
                .flavorId(data.getFlavor().getId())
                .imageId(UUID.fromString(data.getImage().getId()))
                .name("name")
                .description("description")
                .status(GameServerStatus.ACTIVE)
                .build();

        GameServer gameServer = gameServerService.handleCreated(event);

        Assertions.assertEquals(GameServerStatus.ACTIVE, gameServer.getStatus());
    }

    @Test
    public void whenEventHasNullStatusThenExpectException() {

        GameServerCreatedEvent event = GameServerCreatedEvent.builder()
                .id(UUID.randomUUID())
                .gameId(data.getGame().getId())
                .regionId(UUID.fromString(data.getRegion().getId()))
                .flavorId(data.getFlavor().getId())
                .imageId(UUID.fromString(data.getImage().getId()))
                .name("name")
                .description("description")
                .status(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () ->gameServerService.handleCreated(event));
    }

    @Test
    public void whenEventIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> gameServerService.handleCreated(null));
    }

    @Test
    public void whenEventHasNullGameIdThenExpectException() {

        GameServerCreatedEvent event = GameServerCreatedEvent.builder()
                .id(UUID.randomUUID())
                .gameId(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> gameServerService.handleCreated(event));
    }

    @Test
    public void whenEventHasInvalidGameIdThenExpectException() {

        GameServerCreatedEvent event = GameServerCreatedEvent.builder()
                .id(UUID.randomUUID())
                .gameId(UUID.randomUUID())
                .regionId(UUID.fromString(data.getRegion().getId()))
                .flavorId(data.getFlavor().getId())
                .imageId(UUID.fromString(data.getImage().getId()))
                .name("name")
                .status(GameServerStatus.ACTIVE)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> gameServerService.handleCreated(event));
    }

    @Test
    public void whenEventHasNullRegionIdThenExpectException() {

        GameServerCreatedEvent event = GameServerCreatedEvent.builder()
                .id(UUID.randomUUID())
                .gameId(data.getGame().getId())
                .regionId(null)
                .flavorId(data.getFlavor().getId())
                .imageId(UUID.fromString(data.getImage().getId()))
                .name("name")
                .status(GameServerStatus.ACTIVE)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> gameServerService.handleCreated(event));
    }

    @Test
    public void whenEventHasInvalidRegionIdThenExpectException() {

        GameServerCreatedEvent event = GameServerCreatedEvent.builder()
                .id(UUID.randomUUID())
                .gameId(data.getGame().getId())
                .regionId(UUID.randomUUID())
                .flavorId(data.getFlavor().getId())
                .imageId(UUID.fromString(data.getImage().getId()))
                .name("name")
                .status(GameServerStatus.ACTIVE)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> gameServerService.handleCreated(event));
    }

    @Test
    public void whenEventHasNullFlavorIdThenExpectException() {

        GameServerCreatedEvent event = GameServerCreatedEvent.builder()
                .id(UUID.randomUUID())
                .gameId(data.getGame().getId())
                .regionId(UUID.fromString(data.getRegion().getId()))
                .flavorId(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> gameServerService.handleCreated(event));
    }

    @Test
    public void whenEventHasInvalidFlavorIdThenExpectException() {

        GameServerCreatedEvent event = GameServerCreatedEvent.builder()
                .id(UUID.randomUUID())
                .gameId(data.getGame().getId())
                .regionId(UUID.fromString(data.getRegion().getId()))
                .flavorId(UUID.randomUUID())
                .imageId(UUID.fromString(data.getImage().getId()))
                .name("name")
                .status(GameServerStatus.ACTIVE)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> gameServerService.handleCreated(event));
    }

    @Test
    public void whenEventHasNullImageIdThenExpectException() {

        GameServerCreatedEvent event = GameServerCreatedEvent.builder()
                .id(UUID.randomUUID())
                .gameId(data.getGame().getId())
                .regionId(UUID.fromString(data.getRegion().getId()))
                .flavorId(data.getFlavor().getId())
                .imageId(null)
                .status(GameServerStatus.ACTIVE)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> gameServerService.handleCreated(event));
    }

    @Test
    public void whenEventHasInvalidImageIdThenExpectException() {

        GameServerCreatedEvent event = GameServerCreatedEvent.builder()
                .id(UUID.randomUUID())
                .gameId(data.getGame().getId())
                .regionId(UUID.fromString(data.getRegion().getId()))
                .flavorId(data.getFlavor().getId())
                .imageId(UUID.randomUUID())
                .name("name")
                .status(GameServerStatus.ACTIVE)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> gameServerService.handleCreated(event));
    }

    @Test
    public void whenEventHasNullNameTHenExpectException() {

        GameServerCreatedEvent event = GameServerCreatedEvent.builder()
                .id(UUID.randomUUID())
                .gameId(data.getGame().getId())
                .regionId(UUID.fromString(data.getRegion().getId()))
                .flavorId(data.getFlavor().getId())
                .imageId(UUID.fromString(data.getImage().getId()))
                .name(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> gameServerService.handleCreated(event));
    }
}
