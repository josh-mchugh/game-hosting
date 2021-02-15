package com.example.demo.web.admin.game.projection.service.model;

import com.example.demo.web.admin.game.projection.service.projection.AdminGameServerGameProjection;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Collection;

@Value
@AllArgsConstructor
public class FetchAdminGameServerGamesResponse {

    Collection<AdminGameServerGameProjection> games;
}
