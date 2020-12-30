package com.example.demo.project.projection.model;

import com.example.demo.game.entity.GameType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class FetchProjectDetailsByIdProjection {

    String name;
    GameType gameType;

    @QueryProjection
    public FetchProjectDetailsByIdProjection(String name, GameType gameType) {

        this.name = name;
        this.gameType = gameType;
    }
}
