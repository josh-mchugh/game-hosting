package com.example.demo.web.admin.game.service;

import com.example.demo.game.entity.QGameEntity;
import com.example.demo.game.entity.QGameServerEntity;
import com.example.demo.ovh.flavor.entity.QFlavorEntity;
import com.example.demo.ovh.image.entity.QImageEntity;
import com.example.demo.ovh.region.entity.QRegionEntity;
import com.example.demo.web.admin.game.service.model.ExistsGameServerByNameQuery;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerGamesQuery;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerGamesResponse;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerImagesQuery;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerImagesResponse;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerRegionsQuery;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerRegionsResponse;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerTableQuery;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerTableResponse;
import com.example.demo.web.admin.game.service.projection.AdminGameServerFlavorProjection;
import com.example.demo.web.admin.game.service.projection.AdminGameServerGameProjection;
import com.example.demo.web.admin.game.service.projection.AdminGameServerImageProjection;
import com.example.demo.web.admin.game.service.projection.AdminGameServerRegionProjection;
import com.example.demo.web.admin.game.service.projection.AdminGameServerTableProjection;
import com.example.demo.web.admin.game.service.model.ExistsGameServerByNameResponse;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerFlavorsQuery;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerFlavorsResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminGameServerServiceImpl implements AdminGameServerService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public ExistsGameServerByNameResponse existsByName(ExistsGameServerByNameQuery query) {

        QGameServerEntity qGameServer = QGameServerEntity.gameServerEntity;

        long count = queryFactory.select(qGameServer.id)
                .from(qGameServer)
                .where(qGameServer.name.eq(query.getName()))
                .fetchCount();

        return new ExistsGameServerByNameResponse(count >= 1);
    }

    @Override
    @QueryHandler
    public FetchAdminGameServerGamesResponse getGames(FetchAdminGameServerGamesQuery query) {

        QGameEntity qGame = QGameEntity.gameEntity;

        List<AdminGameServerGameProjection> projections = queryFactory.select(
                Projections.constructor(
                    AdminGameServerGameProjection.class,
                    qGame.id,
                    qGame.type
                ))
                .from(qGame)
                .fetch();

        return new FetchAdminGameServerGamesResponse(projections);
    }

    @Override
    @QueryHandler
    public FetchAdminGameServerRegionsResponse getRegions(FetchAdminGameServerRegionsQuery query) {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        List<AdminGameServerRegionProjection> projections = queryFactory.select(
                Projections.constructor(
                        AdminGameServerRegionProjection.class,
                        qRegion.id,
                        qRegion.name,
                        qRegion.dataCenterLocation,
                        qRegion.status
                ))
                .from(qRegion)
                .fetch();

        return new FetchAdminGameServerRegionsResponse(projections);
    }

    @Override
    @QueryHandler
    public FetchAdminGameServerFlavorsResponse getFlavors(FetchAdminGameServerFlavorsQuery query) {

        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;

        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(qFlavor.regionEntity.id.eq(query.getRegionId()));
        predicate.and(qFlavor.type.eq("ovh.vps-ssd"));

        if (StringUtils.isNotBlank(query.getSearch())) {

            predicate.and(qFlavor.name.containsIgnoreCase(query.getSearch()));
        }

        List<AdminGameServerFlavorProjection> projections = queryFactory.select(
                Projections.constructor(
                        AdminGameServerFlavorProjection.class,
                        qFlavor.id,
                        qFlavor.name,
                        qFlavor.vcpus,
                        qFlavor.ram
                ))
                .from(qFlavor)
                .where(predicate)
                .orderBy(qFlavor.name.asc())
                .fetch();

        return new FetchAdminGameServerFlavorsResponse(projections);
    }

    @Override
    @QueryHandler
    public FetchAdminGameServerImagesResponse getImages(FetchAdminGameServerImagesQuery query) {

        QImageEntity qImage = QImageEntity.imageEntity;

        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(qImage.regionEntity.id.eq(query.getRegionId()));
        predicate.and(qImage.name.containsIgnoreCase("ubuntu"));

        if (StringUtils.isNotBlank(query.getSearch())) {

            predicate.and(qImage.name.containsIgnoreCase(query.getSearch()));
        }

        List<AdminGameServerImageProjection> projections = queryFactory.select(
                Projections.constructor(
                        AdminGameServerImageProjection.class,
                        qImage.id,
                        qImage.name
                ))
                .from(qImage)
                .where(predicate)
                .orderBy(qImage.name.asc())
                .fetch();

        return new FetchAdminGameServerImagesResponse(projections);
    }

    @Override
    @QueryHandler
    public FetchAdminGameServerTableResponse getTable(FetchAdminGameServerTableQuery request) {

        QGameServerEntity qGameServer = QGameServerEntity.gameServerEntity;
        QGameEntity qGame = QGameEntity.gameEntity;
        QRegionEntity qRegion = QRegionEntity.regionEntity;
        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;
        QImageEntity qImage = QImageEntity.imageEntity;

        JPQLQuery<AdminGameServerTableProjection> query = queryFactory.select(Projections.constructor(
                    AdminGameServerTableProjection.class,
                    qGameServer.id,
                    qGameServer.name,
                    qGame.type,
                    qRegion.name,
                    qFlavor.vcpus,
                    qFlavor.ram,
                    qImage.name
                ))
                .from(qGameServer)
                .innerJoin(qGameServer.gameEntity, qGame)
                .innerJoin(qGameServer.regionEntity, qRegion)
                .innerJoin(qGameServer.flavorEntity, qFlavor)
                .innerJoin(qGameServer.imageEntity, qImage)
                .limit(request.getPageable().getPageSize())
                .offset(request.getPageable().getOffset());

        if (request.getPageable().getSort().isSorted()) {

            Sort.Order order = request.getPageable().getSort().iterator().next();

            switch (order.getProperty()) {
                case "name":
                    query.orderBy(order.isAscending() ? qGameServer.name.asc() : qGameServer.name.desc());
                    break;
                case "gameType":
                    query.orderBy(order.isAscending() ? qGame.type.asc() : qGame.type.desc());
                    break;
                case "regionName":
                    query.orderBy(order.isAscending() ? qRegion.name.asc() : qRegion.name.desc());
                    break;
                case "vcpus":
                    query.orderBy(order.isAscending() ? qFlavor.vcpus.asc() : qFlavor.vcpus.desc());
                    break;
                case "ram":
                    query.orderBy(order.isAscending() ? qFlavor.ram.asc() : qFlavor.ram.desc());
                    break;
                case "imageName":
                    query.orderBy(order.isAscending() ? qImage.name.asc() : qImage.name.desc());
                    break;
            }
        }
        
        return new FetchAdminGameServerTableResponse(new PageImpl<>(query.fetch(), request.getPageable(), query.fetchCount()));
    }
}
