package com.example.demo.web.dashboard.command.service;

import com.example.demo.game.entity.GameType;
import com.example.demo.game.entity.model.Game;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.web.dashboard.command.service.model.FetchGameIdByGameTypeQuery;
import com.example.demo.web.dashboard.command.service.model.FetchGameIdByGameTypeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.lang.reflect.UndeclaredThrowableException;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class DashboardCommandServiceGetGameIdTest {

    @Autowired
    private IDashboardCommandService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.getGameId(null));
    }

    @Test
    public void whenParamHasNullGameTypeThenExpectException() {

        FetchGameIdByGameTypeQuery query = new FetchGameIdByGameTypeQuery(null);

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.getGameId(query));
    }

    @Test
    public void whenGameExistsThenExpectId() {

        Game game = sampleBuilder.builder()
                .game()
                .build()
                .getGame();

        FetchGameIdByGameTypeQuery query = new FetchGameIdByGameTypeQuery(GameType.MINECRAFT_JAVA);
        FetchGameIdByGameTypeResponse response = service.getGameId(query);

        Assertions.assertEquals(game.getId(), response.getId());
    }

    @Test
    public void whenGameDoesNotExistsThenReturnNull() {

        FetchGameIdByGameTypeQuery query = new FetchGameIdByGameTypeQuery(GameType.MINECRAFT_JAVA);
        FetchGameIdByGameTypeResponse response = service.getGameId(query);

        Assertions.assertNull(response.getId());
    }
}
