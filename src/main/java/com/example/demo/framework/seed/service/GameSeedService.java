package com.example.demo.framework.seed.service;

import com.example.demo.framework.seed.ISeedService;
import com.example.demo.game.aggregate.command.GameCreateCommand;
import com.example.demo.game.entity.GameType;
import com.example.demo.game.projection.IGameProjection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GameSeedService implements ISeedService<Object> {

    private final IGameProjection gameProjection;
    private final CommandGateway commandGateway;

    @Override
    public boolean dataNotExists() {

        return !gameProjection.existsAny();
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
