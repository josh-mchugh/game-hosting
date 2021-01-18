package com.example.demo.game.entity.service;

import com.example.demo.game.aggregate.event.GameServerCreatedEvent;
import com.example.demo.game.entity.model.GameServer;

public interface IGameServerService {

    GameServer handleCreated(GameServerCreatedEvent event);
}
