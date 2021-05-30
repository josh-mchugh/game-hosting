package com.example.demo.web.project.dashboard.projection.projection;

import com.example.demo.game.entity.GameType;
import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class ProjectDetailsProjection {

    String name;
    GameType gameType;
    ProjectStatus status;
    ProjectState state;

    @QueryProjection
    public ProjectDetailsProjection(String name, GameType gameType, ProjectStatus status, ProjectState state) {

        this.name = name;
        this.gameType = gameType;
        this.status = status;
        this.state = state;
    }
}
