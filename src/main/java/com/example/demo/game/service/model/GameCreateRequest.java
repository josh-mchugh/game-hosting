package com.example.demo.game.service.model;

import com.example.demo.game.entity.GameType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class GameCreateRequest {

    GameType type;
}
