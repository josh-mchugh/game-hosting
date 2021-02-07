package com.example.demo.game.projection.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class ExistsGameServerByConfigQuery {

    String gameId;
    String regionId;
    String flavorId;
    String imageId;
}
