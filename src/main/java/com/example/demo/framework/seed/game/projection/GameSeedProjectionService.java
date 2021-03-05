package com.example.demo.framework.seed.game.projection;

import com.example.demo.framework.seed.game.projection.model.ExistsAnyGamesQuery;
import com.example.demo.framework.seed.game.projection.model.ExistsAnyGamesResponse;
import com.example.demo.game.entity.QGameEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameSeedProjectionService implements IGameSeedProjectionService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public ExistsAnyGamesResponse existsAny(ExistsAnyGamesQuery query) {

        QGameEntity qGame = QGameEntity.gameEntity;

        long count = queryFactory.select(qGame.id)
                .from(qGame)
                .fetchCount();

        return new ExistsAnyGamesResponse(count >= 1);
    }
}
