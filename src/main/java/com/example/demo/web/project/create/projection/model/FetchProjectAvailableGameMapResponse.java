package com.example.demo.web.project.create.projection.model;

import com.example.demo.game.entity.GameType;
import lombok.Value;

import java.util.Map;

@Value
public class FetchProjectAvailableGameMapResponse {

    Map<String, GameType> availableGames;
}
