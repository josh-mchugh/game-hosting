package com.example.demo.game.entity.model;

import com.example.demo.game.entity.GameServerStatus;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class GameServer {

    UUID id;
    String name;
    String description;
    GameServerStatus status;
}
