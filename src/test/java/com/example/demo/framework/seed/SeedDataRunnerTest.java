package com.example.demo.framework.seed;

import com.example.demo.framework.properties.AppConfig;
import com.example.demo.framework.seed.game.GameSeedService;
import com.example.demo.framework.seed.game.projection.model.ExistsAnyGamesQuery;
import com.example.demo.framework.seed.game.projection.model.ExistsAnyGamesResponse;
import com.example.demo.game.aggregate.command.GameCreateCommand;
import com.example.demo.game.entity.GameType;
import com.google.common.collect.ImmutableMap;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SeedDataRunnerTest {

    @Test
    public void whenSeedDataRunnerSeedsThenExpectNoException() throws ExecutionException, InterruptedException {

        AppConfig config = new AppConfig();
        config.setEnableSeedData(true);

        QueryGateway queryGateway = Mockito.mock(QueryGateway.class);
        Mockito.when(queryGateway.query(new ExistsAnyGamesQuery(), ExistsAnyGamesResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsAnyGamesResponse(false)));

        CommandGateway commandGateway = Mockito.mock(CommandGateway.class);
        Mockito.when(commandGateway.sendAndWait(Mockito.any(GameCreateCommand.class))).thenReturn(UUID.randomUUID());

        ApplicationContext applicationContext = Mockito.mock(ApplicationContext.class);

        SeedDataRunner runner = new SeedDataRunner(config);
        runner.seedServices = ImmutableMap.of(GameSeedService.class.getCanonicalName(), new GameSeedService(queryGateway, commandGateway));

        runner.onContextRefreshedEvent(new ContextRefreshedEvent(applicationContext));

        Mockito.verify(commandGateway, Mockito.times(GameType.values().length)).sendAndWait(Mockito.any(GameCreateCommand.class));
    }
}
