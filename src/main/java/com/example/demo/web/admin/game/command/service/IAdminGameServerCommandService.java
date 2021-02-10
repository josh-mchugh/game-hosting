package com.example.demo.web.admin.game.command.service;

import com.example.demo.web.admin.game.command.service.model.GameServerCreateRequest;

public interface IAdminGameServerCommandService {

    void handleGameServerCreate(GameServerCreateRequest request);

    boolean existsByName(String name);
}
