package com.example.demo.framework.seed;

import com.example.demo.framework.properties.AppConfig;
import com.example.demo.framework.seed.service.GameSeedService;
import com.example.demo.game.aggregate.command.GameCreateCommand;
import com.example.demo.game.entity.GameType;
import com.example.demo.game.projection.IGameProjector;
import com.google.common.collect.ImmutableMap;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.UUID;

public class SeedDataRunnerTest {

    @Test
    public void whenSeedDataRunnerSeedsThenExpectNoException() {

        AppConfig config = new AppConfig();
        config.setEnableSeedData(true);

        IGameProjector gameProjection = Mockito.mock(IGameProjector.class);
        Mockito.when(gameProjection.existsAny()).thenReturn(false);

        CommandGateway commandGateway = Mockito.mock(CommandGateway.class);
        Mockito.when(commandGateway.sendAndWait(Mockito.any(GameCreateCommand.class))).thenReturn(UUID.randomUUID());

        ApplicationContext applicationContext = Mockito.mock(ApplicationContext.class);

        SeedDataRunner runner = new SeedDataRunner(config);
        runner.seedServices = ImmutableMap.of(GameSeedService.class.getCanonicalName(), new GameSeedService(gameProjection, commandGateway));

        runner.onContextRefreshedEvent(new ContextRefreshedEvent(applicationContext));

        Mockito.verify(commandGateway, Mockito.times(GameType.values().length)).sendAndWait(Mockito.any(GameCreateCommand.class));
    }
}
