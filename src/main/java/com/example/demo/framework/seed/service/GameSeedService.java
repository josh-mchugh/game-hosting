package com.example.demo.framework.seed.service;

import com.example.demo.framework.properties.AppConfig;
import com.example.demo.framework.seed.ISeedService;
import com.example.demo.game.entity.GameType;
import com.example.demo.game.model.Game;
import com.example.demo.game.service.IGameService;
import com.example.demo.game.service.model.GameCreateRequest;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameSeedService implements ISeedService<Game> {

    private final AppConfig appConfig;
    private final IGameService gameService;

    @Override
    public boolean dataNotExists() {

        return !gameService.existsAny();
    }

    @Override
    public ImmutableList<Game> initializeData() {

        return Lists.newArrayList(GameType.values()).stream()
                .map(type -> GameCreateRequest.builder().type(type).build())
                .map(gameService::handleGameCreateRequest)
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
}
