package com.example.demo.game.entity;

import com.example.demo.game.aggregate.event.GameCreatedEvent;
import com.example.demo.game.entity.model.Game;
import com.example.demo.game.entity.service.IGameService;
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
    private IGameService gameService;

    @Test
    public void whenRequestIsNullThenThrowException() {

       Assertions.assertThrows(NullPointerException.class, () -> gameService.handleCreated(null));
    }

    @Test
    public void whenRequestIsValidThenReturnGame() {

        GameCreatedEvent event = new GameCreatedEvent(UUID.randomUUID(), GameType.MINECRAFT_JAVA);
        Game game = gameService.handleCreated(event);

        Assertions.assertNotNull(game);
    }

    @Test
    public void whenRequestIsValidThenReturnId() {

        UUID id = UUID.randomUUID();

        GameCreatedEvent event = new GameCreatedEvent(id, GameType.MINECRAFT_JAVA);
        Game game = gameService.handleCreated(event);

        Assertions.assertEquals(id.toString(), game.getId());
    }

    @Test
    public void whenRequestHasGameTypeReturnGameType() {

        GameCreatedEvent event = new GameCreatedEvent(UUID.randomUUID(), GameType.MINECRAFT_JAVA);
        Game game = gameService.handleCreated(event);

        Assertions.assertEquals(GameType.MINECRAFT_JAVA, game.getType());
    }

    @Test
    public void whenRequestHasNullGameTypeThenThrowException() {

        GameCreatedEvent event = new GameCreatedEvent(UUID.randomUUID(), null);

        Assertions.assertThrows(PersistenceException.class, () -> gameService.handleCreated(event));
    }
}
