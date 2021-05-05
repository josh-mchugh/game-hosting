package com.example.demo.web.project.create.projection;

import com.example.demo.game.entity.GameType;
import com.example.demo.game.entity.QGameEntity;
import com.example.demo.game.entity.QGameServerEntity;
import com.example.demo.web.project.create.projection.model.FetchProjectAvailableGameMapQuery;
import com.example.demo.web.project.create.projection.model.FetchProjectAvailableGameMapResponse;
import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProjectCreateProjectionService implements IProjectCreateProjectionService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public FetchProjectAvailableGameMapResponse fetchAvailableGameMap(FetchProjectAvailableGameMapQuery request) {

        QGameEntity qGame = QGameEntity.gameEntity;
        QGameServerEntity qGameServer = QGameServerEntity.gameServerEntity;

        Map<String, GameType> gameMap = queryFactory.select(qGame.id, qGame.type)
                .from(qGameServer)
                .innerJoin(qGameServer.gameEntity, qGame)
                .transform(GroupBy.groupBy(qGame.id).as(qGame.type));

        return new FetchProjectAvailableGameMapResponse(gameMap);
    }
}
