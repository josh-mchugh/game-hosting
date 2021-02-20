package com.example.demo.web.project.projection.service.projection;

import com.example.demo.game.entity.GameType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class ProjectDetailsProjection {

    String name;
    GameType gameType;

    @QueryProjection
    public ProjectDetailsProjection(String name, GameType gameType) {

        this.name = name;
        this.gameType = gameType;
    }
}
