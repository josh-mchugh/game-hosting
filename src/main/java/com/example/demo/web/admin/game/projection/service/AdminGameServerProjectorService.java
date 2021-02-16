package com.example.demo.web.admin.game.projection.service;

import com.example.demo.framework.web.Select2Response;
import com.example.demo.game.entity.QGameEntity;
import com.example.demo.game.entity.QGameServerEntity;
import com.example.demo.ovh.flavor.entity.QFlavorEntity;
import com.example.demo.ovh.flavor.projection.IFlavorProjector;
import com.example.demo.ovh.flavor.projection.model.AdminGameServerFlavorProjection;
import com.example.demo.ovh.flavor.projection.model.FetchAdminGameServerFlavorsQuery;
import com.example.demo.ovh.flavor.projection.model.FetchAdminGameServerFlavorsResponse;
import com.example.demo.ovh.image.entity.QImageEntity;
import com.example.demo.ovh.image.projection.IImageProjector;
import com.example.demo.ovh.image.projection.model.AdminGameServerImageProjection;
import com.example.demo.ovh.image.projection.model.FetchAdminGameServerImagesQuery;
import com.example.demo.ovh.image.projection.model.FetchAdminGameServerImagesResponse;
import com.example.demo.ovh.region.entity.QRegionEntity;
import com.example.demo.web.admin.game.projection.model.AdminGameServerPageRequest;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerGamesQuery;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerGamesResponse;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerRegionsQuery;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerRegionsResponse;
import com.example.demo.web.admin.game.projection.service.projection.AdminGameServerGameProjection;
import com.example.demo.web.admin.game.projection.service.projection.AdminGameServerRegionProjection;
import com.example.demo.web.admin.game.projection.service.projection.AdminGameServerTableProjection;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminGameServerProjectorService implements IAdminGameServerProjectorService {

    private final IFlavorProjector flavorProjector;
    private final IImageProjector imageProjector;

    private final JPQLQueryFactory queryFactory;

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
    public Select2Response<AdminGameServerFlavorProjection> getFlavors(String search, String regionId) {

        FetchAdminGameServerFlavorsQuery query = new FetchAdminGameServerFlavorsQuery(search, regionId);
        FetchAdminGameServerFlavorsResponse response = flavorProjector.fetchFlavorsByRegionId(query);

        return new Select2Response<>(response.getFlavors());
    }

    @Override
    public Select2Response<AdminGameServerImageProjection> getImages(String search, String regionId) {

        FetchAdminGameServerImagesQuery query = new FetchAdminGameServerImagesQuery(search, regionId);
        FetchAdminGameServerImagesResponse response = imageProjector.fetchImagesByRegionId(query);

        return new Select2Response<>(response.getImages());
    }

    @Override
    public Page<AdminGameServerTableProjection> getPage(AdminGameServerPageRequest request) {

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
        
        return new PageImpl<>(query.fetch(), request.getPageable(), query.fetchCount());
    }
}
