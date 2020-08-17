package com.example.demo.project.service.model;

import com.example.demo.game.entity.GameType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class ProjectCreateRequest {

    String name;
    String userId;
    GameType gameType;
}
