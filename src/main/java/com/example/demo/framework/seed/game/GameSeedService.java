package com.example.demo.framework.seed.game;

import com.example.demo.framework.seed.ISeedService;
import com.example.demo.framework.seed.game.projection.model.ExistsAnyGamesQuery;
import com.example.demo.framework.seed.game.projection.model.ExistsAnyGamesResponse;
import com.example.demo.game.aggregate.command.GameCreateCommand;
import com.example.demo.game.entity.GameType;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class GameSeedService implements ISeedService<Object> {

    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    @Override
    public boolean dataNotExists() throws ExecutionException, InterruptedException {

        ExistsAnyGamesQuery query = new ExistsAnyGamesQuery();
        ExistsAnyGamesResponse response = queryGateway.query(query, ExistsAnyGamesResponse.class).get();

        return !response.exists();
    }

    @Override
    public ImmutableList<Object> initializeData() {

        return Lists.newArrayList(GameType.values()).stream()
                .map(this::createCommand)
                .map(commandGateway::sendAndWait)
                .collect(ImmutableList.toImmutableList());
    }

    @Override
    public String type() {

        return "Game";
    }

    @Override
    public Integer order() {

        return 5;
    }

    private GameCreateCommand createCommand(GameType type) {

        return GameCreateCommand.builder()
                .id(UUID.randomUUID())
                .type(type)
                .build();
    }
}
