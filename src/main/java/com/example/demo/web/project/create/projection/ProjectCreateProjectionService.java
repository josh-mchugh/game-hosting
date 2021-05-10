package com.example.demo.web.project.create.projection;

import com.example.demo.game.entity.GameEntity;
import com.example.demo.game.entity.GameType;
import com.example.demo.game.entity.QGameEntity;
import com.example.demo.game.entity.QGameServerEntity;
import com.example.demo.ovh.flavor.entity.QFlavorEntity;
import com.example.demo.ovh.region.entity.QRegionEntity;
import com.example.demo.ovh.region.entity.RegionEntity;
import com.example.demo.project.entity.QProjectEntity;
import com.example.demo.web.project.create.projection.model.FetchProjectAvailableGameMapQuery;
import com.example.demo.web.project.create.projection.model.FetchProjectAvailableGameMapResponse;
import com.example.demo.web.project.create.projection.model.FetchProjectAvailableRegionsMapQuery;
import com.example.demo.web.project.create.projection.model.FetchProjectAvailableRegionsMapResponse;
import com.example.demo.web.project.create.projection.model.FetchProjectAvailableServersMapQuery;
import com.example.demo.web.project.create.projection.model.FetchProjectAvailableServersMapResponse;
import com.google.common.collect.ImmutableMap;
import com.querydsl.core.BooleanBuilder;
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

        JPQLQuery<GameEntity> subquery = JPAExpressions.select(qProject.gameEntity)
                .from(qProject)
                .where(qProject.id.eq(query.getId().toString()));

        Map<String, String> results = queryFactory.select(qRegion.id, qRegion.name)
                .from(qGameServer)
                .innerJoin(qGameServer.regionEntity, qRegion)
                .where(qGameServer.gameEntity.eq(subquery))
                .transform(GroupBy.groupBy(qRegion.id).as(qRegion.name));

        return new FetchProjectAvailableRegionsMapResponse(ImmutableMap.copyOf(results));
    }

    @Override
    @QueryHandler
    public FetchProjectAvailableServersMapResponse fetchAvailableServersMap(FetchProjectAvailableServersMapQuery query) {

        QProjectEntity qProject = QProjectEntity.projectEntity;
        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;
        QGameServerEntity qGameServer = QGameServerEntity.gameServerEntity;

        JPQLQuery<GameEntity> gameSubQuery = JPAExpressions.select(qProject.gameEntity)
                .from(qProject)
                .where(qProject.id.eq(query.getId().toString()));

        JPQLQuery<RegionEntity> regionSubQuery = JPAExpressions.select(qProject.regionEntity)
                .from(qProject)
                .where(qProject.id.eq(query.getId().toString()));

        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(qGameServer.gameEntity.eq(gameSubQuery));
        predicate.and(qGameServer.regionEntity.eq(regionSubQuery));

        Map<String, String> results = queryFactory.select(qFlavor.id, qFlavor.name)
                .from(qGameServer)
                .innerJoin(qGameServer.flavorEntity, qFlavor)
                .where(predicate)
                .transform(GroupBy.groupBy(qFlavor.id).as(qFlavor.name));

        return new FetchProjectAvailableServersMapResponse(ImmutableMap.copyOf(results));
    }
}