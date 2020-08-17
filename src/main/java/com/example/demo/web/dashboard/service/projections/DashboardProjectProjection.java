package com.example.demo.web.dashboard.service.projections;

import com.example.demo.game.entity.GameType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class DashboardProjectProjection {

    String id;
    String name;
    GameType gameType;

    @QueryProjection
    public DashboardProjectProjection(String id, String name, GameType gameType) {

        this.id = id;
        this.name = name;
        this.gameType = gameType;
    }
}
