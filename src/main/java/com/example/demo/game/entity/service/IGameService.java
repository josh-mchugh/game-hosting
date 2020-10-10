package com.example.demo.game.entity.service;

import com.example.demo.game.aggregate.event.GameCreatedEvent;
import com.example.demo.game.entity.model.Game;

public interface IGameService {

    Game handleCreated(GameCreatedEvent event);
}
