package com.example.demo.game.projection;

import com.example.demo.game.entity.GameType;
import com.example.demo.game.entity.model.Game;
import com.example.demo.game.projection.model.FetchAdminGameServerGamesQuery;
import com.example.demo.game.projection.model.FetchAdminGameServerGamesResponse;

public interface IGameProjector {

    boolean existsAny();

    Game getGameByType(GameType type);

    FetchAdminGameServerGamesResponse fetchGames(FetchAdminGameServerGamesQuery query);
}
