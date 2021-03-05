package com.example.demo.framework.seed.game;

import com.example.demo.framework.seed.game.projection.model.ExistsAnyGamesQuery;
import com.example.demo.framework.seed.game.projection.model.ExistsAnyGamesResponse;
import com.example.demo.game.entity.GameType;
import com.example.demo.sample.SampleBuilder;
import com.google.common.collect.ImmutableList;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class GameSeedServiceTest {

    @Autowired
    private GameSeedService gameSeedService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    private QueryGateway queryGateway;

    @Test
    public void whenGameExistsThenDoesNotExistsReturnFalse() throws ExecutionException, InterruptedException {

        Mockito.when(queryGateway.query(new ExistsAnyGamesQuery(), ExistsAnyGamesResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsAnyGamesResponse(true)));

        Assertions.assertFalse(gameSeedService.dataNotExists());
    }

    @Test
    public void whenGameExistsThenDoesNotExistReturnsTrue() throws ExecutionException, InterruptedException {

        Mockito.when(queryGateway.query(new ExistsAnyGamesQuery(), ExistsAnyGamesResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsAnyGamesResponse(false)));

        Assertions.assertTrue(gameSeedService.dataNotExists());
    }

    @Test
    public void whenGameIsInitializedThenReturnList() {

        ImmutableList<Object> games = gameSeedService.initializeData();

        Assertions.assertEquals(GameType.values().length, games.size());
    }

    @Test
    public void whenTypeHasValueThenReturnValue() {

        Assertions.assertEquals("Game", gameSeedService.type());
    }

    @Test
    public void typeShouldNotBeNull() {

        Assertions.assertNotNull(gameSeedService.type());
    }

    @Test
    public void whenOrderHasValueThenReturnValue() {

        Assertions.assertEquals(5, gameSeedService.order());
    }

    @Test
    public void orderShouldNotBeNull() {

        Assertions.assertNotNull(gameSeedService.order());
    }
}
