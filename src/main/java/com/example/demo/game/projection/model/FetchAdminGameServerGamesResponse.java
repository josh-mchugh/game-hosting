package com.example.demo.game.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Collection;

@Value
@AllArgsConstructor
public class FetchAdminGameServerGamesResponse {

    Collection<AdminGameServerGameProjection> games;
}
