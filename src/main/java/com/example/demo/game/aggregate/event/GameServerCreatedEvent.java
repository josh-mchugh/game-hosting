package com.example.demo.game.aggregate.event;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class GameServerCreatedEvent {

    UUID id;
    UUID gameId;
    UUID regionId;
    UUID flavorId;
    UUID imageId;
    String name;
    String description;
}
