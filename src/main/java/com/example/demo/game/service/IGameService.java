package com.example.demo.game.service;

import com.example.demo.game.model.Game;
import com.example.demo.game.service.model.GameCreateRequest;

public interface IGameService {

    boolean existsAny();

    Game handleGameCreateRequest(GameCreateRequest request);
}
