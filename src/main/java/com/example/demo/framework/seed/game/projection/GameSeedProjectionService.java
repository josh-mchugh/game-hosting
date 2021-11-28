package com.example.demo.framework.seed.game.projection;

import com.example.demo.framework.seed.game.projection.model.ExistsAnyGamesQuery;
import com.example.demo.framework.seed.game.projection.model.ExistsAnyGamesResponse;

public interface GameSeedProjectionService {

    ExistsAnyGamesResponse existsAny(ExistsAnyGamesQuery query);
}
