package com.example.demo.game.projection;

import com.example.demo.game.entity.QGameServerEntity;
import com.example.demo.game.projection.model.ExistsGameServerByNameQuery;
import com.example.demo.game.projection.model.ExistsGameServerByNameResponse;
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
}
