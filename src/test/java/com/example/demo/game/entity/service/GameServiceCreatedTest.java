package com.example.demo.game.entity.service;

import com.example.demo.game.aggregate.event.GameCreatedEvent;
import com.example.demo.game.entity.GameType;
import com.example.demo.game.entity.model.Game;
import org.junit.jupiter.api.Assertions;
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
public class GameServiceCreatedTest {

    @Autowired
    private GameService gameService;

    @Test
    public void whenEventIsNullThenThrowException() {

       Assertions.assertThrows(NullPointerException.class, () -> gameService.handleCreated(null));
    }

    @Test
    public void whenEventIsValidThenReturnGame() {

        GameCreatedEvent event = GameCreatedEvent.builder()
                .id(UUID.randomUUID())
                .type(GameType.MINECRAFT_JAVA)
                .build();

        Game game = gameService.handleCreated(event);

        Assertions.assertNotNull(game);
    }

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        GameCreatedEvent event = GameCreatedEvent.builder()
                .id(id)
                .type(GameType.MINECRAFT_JAVA)
                .build();

        Game game = gameService.handleCreated(event);

        Assertions.assertEquals(id, game.getId());
    }

    @Test
    public void whenEventHasNullIdThenThrowException() {

        GameCreatedEvent event = GameCreatedEvent.builder()
                .id(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> gameService.handleCreated(event));
    }

    @Test
    public void whenEventHasGameTypeReturnGameType() {

        GameCreatedEvent event = GameCreatedEvent.builder()
                .id(UUID.randomUUID())
                .type(GameType.MINECRAFT_JAVA)
                .build();

        Game game = gameService.handleCreated(event);

        Assertions.assertEquals(GameType.MINECRAFT_JAVA, game.getType());
    }

    @Test
    public void whenEventHasNullGameTypeThenThrowException() {

        GameCreatedEvent event = GameCreatedEvent.builder()
                .id(UUID.randomUUID())
                .type(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> gameService.handleCreated(event));
    }
}
