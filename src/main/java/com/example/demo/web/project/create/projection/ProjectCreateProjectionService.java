package com.example.demo.web.project.create.projection;

import com.example.demo.game.entity.GameEntity;
import com.example.demo.game.entity.GameType;
import com.example.demo.game.entity.QGameEntity;
import com.example.demo.game.entity.QGameServerEntity;
import com.example.demo.ovh.region.entity.QRegionEntity;
import com.example.demo.project.entity.QProjectEntity;
import com.example.demo.web.project.create.projection.model.FetchProjectAvailableGameMapQuery;
import com.example.demo.web.project.create.projection.model.FetchProjectAvailableGameMapResponse;
import com.example.demo.web.project.create.projection.model.FetchProjectAvailableRegionsMapQuery;
import com.example.demo.web.project.create.projection.model.FetchProjectAvailableRegionsMapResponse;
import com.google.common.collect.ImmutableMap;
import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
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

        Map<String, GameType> results = queryFactory.select(qGame.id, qGame.type)
                .from(qGameServer)
                .innerJoin(qGameServer.gameEntity, qGame)
                .transform(GroupBy.groupBy(qGame.id).as(qGame.type));

        return new FetchProjectAvailableGameMapResponse(ImmutableMap.copyOf(results));
    }

    @Override
    @QueryHandler
    public FetchProjectAvailableRegionsMapResponse fetchAvailableRegionsMap(FetchProjectAvailableRegionsMapQuery query) {

        QProjectEntity qProject = QProjectEntity.projectEntity;
        QRegionEntity qRegion = QRegionEntity.regionEntity;
        QGameServerEntity qGameServer = QGameServerEntity.gameServerEntity;

        JPQLQuery<GameEntity> tes = JPAExpressions.select(qProject.gameEntity)
                .from(qProject)
                .where(qProject.id.eq(query.getId().toString()));

        Map<String, String> results = queryFactory.select(qRegion.id, qRegion.name)
                .from(qGameServer)
                .innerJoin(qGameServer.regionEntity, qRegion)
                .where(qGameServer.gameEntity.eq(tes))
                .transform(GroupBy.groupBy(qRegion.id).as(qRegion.name));

        return new FetchProjectAvailableRegionsMapResponse(ImmutableMap.copyOf(results));
    }
}
