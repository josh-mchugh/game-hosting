package com.example.demo.web.dashboard.service.projection;

import com.example.demo.game.entity.GameType;
import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

import java.util.UUID;

@Value
public class ProjectDashboardProjection {

    UUID id;
    String name;
    GameType gameType;
    ProjectStatus status;
    ProjectState state;

    @QueryProjection
    public ProjectDashboardProjection(String id, String name, GameType gameType, ProjectStatus status, ProjectState state) {

        this.id = UUID.fromString(id);
        this.name = name;
        this.gameType = gameType;
        this.status = status;
        this.state = state;
    }
}
