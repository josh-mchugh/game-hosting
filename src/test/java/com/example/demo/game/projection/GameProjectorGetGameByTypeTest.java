package com.example.demo.game.projection;

import com.example.demo.game.aggregate.event.GameCreatedEvent;
import com.example.demo.game.entity.GameType;
import com.example.demo.game.entity.model.Game;
import com.example.demo.game.entity.service.IGameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class GameProjectorGetGameByTypeTest {

    @Autowired
    private IGameProjection gameProjection;

    @Autowired
    private IGameService gameService;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> gameProjection.getGameByType(null));
    }

    @Test
    public void whenGameDoesNotExistThenReturnNull() {

        Game game = gameProjection.getGameByType(GameType.MINECRAFT_JAVA);

        Assertions.assertNull(game);
    }

    @Test
    public void whenGameExistsByTypeThenReturnGame() {

        GameCreatedEvent event = GameCreatedEvent.builder()
                .id(UUID.randomUUID())
                .type(GameType.MINECRAFT_JAVA)
                .build();

        gameService.handleCreated(event);

        Game game = gameProjection.getGameByType(GameType.MINECRAFT_JAVA);

        Assertions.assertEquals(GameType.MINECRAFT_JAVA, game.getType());
    }
}
