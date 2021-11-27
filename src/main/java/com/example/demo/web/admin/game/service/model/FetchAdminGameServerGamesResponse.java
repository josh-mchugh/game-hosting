package com.example.demo.web.admin.game.service.model;

import com.example.demo.web.admin.game.service.projection.AdminGameServerGameProjection;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Collection;

@Value
@AllArgsConstructor
public class FetchAdminGameServerGamesResponse {

    Collection<AdminGameServerGameProjection> games;
}
