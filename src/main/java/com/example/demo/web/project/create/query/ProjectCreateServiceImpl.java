package com.example.demo.web.project.create.query;

import com.example.demo.game.entity.GameEntity;
import com.example.demo.game.entity.GameType;
import com.example.demo.game.entity.QGameEntity;
import com.example.demo.game.entity.QGameServerEntity;
import com.example.demo.ovh.flavor.entity.QFlavorEntity;
import com.example.demo.ovh.image.entity.QImageEntity;
import com.example.demo.ovh.region.entity.QRegionEntity;
import com.example.demo.ovh.region.entity.RegionEntity;
import com.example.demo.project.entity.QProjectEntity;
import com.example.demo.web.project.create.query.model.FetchProjectAvailableGameMapQuery;
import com.example.demo.web.project.create.query.model.FetchProjectAvailableGameMapResponse;
import com.example.demo.web.project.create.query.model.FetchProjectAvailableRegionsMapQuery;
import com.example.demo.web.project.create.query.model.FetchProjectAvailableRegionsMapResponse;
import com.example.demo.web.project.create.query.model.FetchProjectAvailableServersMapQuery;
import com.example.demo.web.project.create.query.model.FetchProjectAvailableServersMapResponse;
import com.example.demo.web.project.create.query.model.FetchProjectImageIdQuery;
import com.example.demo.web.project.create.query.model.FetchProjectImageIdResponse;
import com.example.demo.web.project.create.query.model.FetchProjectStatusAndStateQuery;
import com.example.demo.web.project.create.query.model.FetchProjectStatusAndStateResponse;
import com.example.demo.web.project.create.query.projection.ProjectStatusAndStateProjection;
import com.google.common.collect.ImmutableMap;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProjectCreateServiceImpl implements ProjectCreateService {

    private final JPQLQueryFactory queryFactory;

    @Override
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

    @Override
    public FetchProjectStatusAndStateResponse fetchStatusAndState(FetchProjectStatusAndStateQuery query) {

        QProjectEntity qProject = QProjectEntity.projectEntity;

        ProjectStatusAndStateProjection projection = queryFactory.select(
                    Projections.constructor(
                        ProjectStatusAndStateProjection.class,
                        qProject.status,
                        qProject.state
                ))
                .from(qProject)
                .where(qProject.id.eq(query.getId().toString()))
                .fetchOne();

        return new FetchProjectStatusAndStateResponse(projection != null ? projection.getStatus() : null, projection != null ? projection.getState() : null);
    }

    @Override
    public FetchProjectImageIdResponse fetchProjectImageId(FetchProjectImageIdQuery query) {

        QProjectEntity qProject = QProjectEntity.projectEntity;
        QImageEntity qImage = QImageEntity.imageEntity;
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
        predicate.and(qGameServer.flavorEntity.id.eq(query.getFlavorId()));

        String imageId = queryFactory.select(qImage.id)
                .from(qGameServer)
                .innerJoin(qGameServer.imageEntity, qImage)
                .where(predicate)
                .fetchOne();

        return new FetchProjectImageIdResponse(imageId != null ? UUID.fromString(imageId) : null);
    }
}
