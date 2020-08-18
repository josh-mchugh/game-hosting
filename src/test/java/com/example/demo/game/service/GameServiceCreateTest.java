package com.example.demo.game.service;

import com.example.demo.game.entity.GameType;
import com.example.demo.game.model.Game;
import com.example.demo.game.service.model.GameCreateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class GameServiceCreateTest {

    @Autowired
    private IGameService gameService;

    @Test
    public void whenRequestIsNullThenThrowException() {

       Exception exception = Assertions.assertThrows(NullPointerException.class, () -> gameService.handleGameCreateRequest(null));

       Assertions.assertNotNull(exception);
    }

    @Test
    public void whenRequestIsValidThenReturnGame() {

        GameCreateRequest gameCreateRequest = GameCreateRequest.builder()
                .type(GameType.MINECRAFT_JAVA)
                .build();
        Game game = gameService.handleGameCreateRequest(gameCreateRequest);

        Assertions.assertNotNull(game);
    }

    @Test
    public void whenRequestIsValidThenReturnId() {

        GameCreateRequest gameCreateRequest = GameCreateRequest.builder()
                .type(GameType.MINECRAFT_JAVA)
                .build();
        Game game = gameService.handleGameCreateRequest(gameCreateRequest);

        Assertions.assertNotNull(game.getId());
    }

    @Test
    public void whenRequestHasGameTypeReturnGameType() {

        GameCreateRequest gameCreateRequest = GameCreateRequest.builder()
                .type(GameType.MINECRAFT_JAVA)
                .build();
        Game game = gameService.handleGameCreateRequest(gameCreateRequest);

        Assertions.assertEquals(GameType.MINECRAFT_JAVA, game.getType());
    }

    @Test
    public void whenRequestHasNullGameTypeThenThrowException() {

        GameCreateRequest gameCreateRequest = GameCreateRequest.builder().build();

        Exception exception = Assertions.assertThrows(PersistenceException.class, () -> gameService.handleGameCreateRequest(gameCreateRequest));

        Assertions.assertNotNull(exception);
    }
}
