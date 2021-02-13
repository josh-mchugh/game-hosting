package com.example.demo.game.entity.model;

import com.example.demo.game.entity.GameType;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class Game {

    UUID id;
    GameType type;
}
