package com.example.demo.web.admin.game.service.projection;

import com.example.demo.game.entity.GameServerStatus;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class GameServerCreateRequest {

    String name;
    String description;
    GameServerStatus status;
    UUID gameId;
    UUID regionId;
    UUID flavorId;
    UUID imageId;
}
