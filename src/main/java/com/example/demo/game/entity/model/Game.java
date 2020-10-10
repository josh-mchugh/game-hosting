package com.example.demo.game.entity.model;

import com.example.demo.game.entity.GameType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class Game {

    String id;
    GameType type;
}
