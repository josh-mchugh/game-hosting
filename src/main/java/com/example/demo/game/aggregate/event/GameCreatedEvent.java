package com.example.demo.game.aggregate.event;

import com.example.demo.game.entity.GameType;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class GameCreatedEvent {

    UUID id;
    GameType type;
}
