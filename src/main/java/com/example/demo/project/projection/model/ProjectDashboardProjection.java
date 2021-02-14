package com.example.demo.project.projection.model;

import com.example.demo.game.entity.GameType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

import java.util.UUID;

@Value
public class ProjectDashboardProjection {

    UUID id;
    String name;
    GameType gameType;

    @QueryProjection
    public ProjectDashboardProjection(String id, String name, GameType gameType) {

        this.id = UUID.fromString(id);
        this.name = name;
        this.gameType = gameType;
    }
}
