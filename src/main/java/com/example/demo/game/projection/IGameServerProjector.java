package com.example.demo.game.projection;

import com.example.demo.game.projection.model.ExistsGameServerByNameQuery;
import com.example.demo.game.projection.model.ExistsGameServerByNameResponse;

public interface IGameServerProjector {

    ExistsGameServerByNameResponse existsByName(ExistsGameServerByNameQuery query);
}
