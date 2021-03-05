package com.example.demo.framework.seed.game.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@AllArgsConstructor
public class ExistsAnyGamesResponse {

    @Accessors(fluent = true)
    boolean exists;
}
