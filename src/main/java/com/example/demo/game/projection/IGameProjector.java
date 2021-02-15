package com.example.demo.game.projection;

import com.example.demo.game.entity.GameType;
import com.example.demo.game.entity.model.Game;

public interface IGameProjector {

    boolean existsAny();

    Game getGameByType(GameType type);
}
