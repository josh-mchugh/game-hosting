package com.example.demo.web.admin.game.command.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class GameServerExistsByConfig {

    String gameId;
    String regionId;
    String flavorId;
    String imageId;
}
