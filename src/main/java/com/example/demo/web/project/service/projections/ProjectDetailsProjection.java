package com.example.demo.web.project.service.projections;

import com.example.demo.game.entity.GameType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class ProjectDetailsProjection {

    String name;
    GameType gameType;

    @QueryProjection
    public ProjectDetailsProjection(String name, GameType gameType) {

        this.name = name;
        this.gameType = gameType;
    }
}
