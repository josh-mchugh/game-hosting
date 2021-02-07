package com.example.demo.game.projection;

import com.example.demo.game.entity.QGameServerEntity;
import com.example.demo.game.projection.model.ExistsGameServerByConfigQuery;
import com.example.demo.game.projection.model.ExistsGameServerByConfigResponse;
import com.example.demo.game.projection.model.ExistsGameServerByNameQuery;
import com.example.demo.game.projection.model.ExistsGameServerByNameResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameServerProjector implements IGameServerProjector {

    private final JPQLQueryFactory queryFactory;

    @Override
    public ExistsGameServerByNameResponse existsByName(ExistsGameServerByNameQuery query) {

        QGameServerEntity qGameServer = QGameServerEntity.gameServerEntity;

        long count = queryFactory.select(qGameServer.id)
                .from(qGameServer)
                .where(qGameServer.name.eq(query.getName()))
                .fetchCount();

        return new ExistsGameServerByNameResponse(count >= 1);
    }

    @Override
    public ExistsGameServerByConfigResponse existsByConfig(ExistsGameServerByConfigQuery query) {

        QGameServerEntity qGameServer = QGameServerEntity.gameServerEntity;

        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(qGameServer.gameEntity.id.eq(query.getGameId()));
        predicate.and(qGameServer.regionEntity.id.eq(query.getRegionId()));
        predicate.and(qGameServer.flavorEntity.id.eq(query.getFlavorId()));
        predicate.and(qGameServer.imageEntity.id.eq(query.getImageId()));

        long count = queryFactory.select(qGameServer.id)
                .from(qGameServer)
                .where(predicate)
                .fetchCount();

        return new ExistsGameServerByConfigResponse(count >= 1);
    }
}
