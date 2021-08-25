package com.example.demo.web.project.create.query.model;

import com.example.demo.game.entity.GameType;
import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchProjectAvailableGameMapResponse {

    ImmutableMap<String, GameType> availableGames;
}
