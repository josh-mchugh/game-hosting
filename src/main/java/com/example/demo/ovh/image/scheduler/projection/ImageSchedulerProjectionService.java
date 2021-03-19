package com.example.demo.ovh.image.scheduler.projection;

import com.example.demo.ovh.image.entity.QImageEntity;
import com.example.demo.ovh.image.scheduler.projection.model.ExistsImageByNameAndRegionNameQuery;
import com.example.demo.ovh.image.scheduler.projection.model.ExistsImageByNameAndRegionNameResponse;
import com.example.demo.ovh.image.scheduler.projection.model.FetchImageProjectionByNameAndRegionNameQuery;
import com.example.demo.ovh.image.scheduler.projection.model.FetchImageProjectionByNameAndRegionNameResponse;
import com.example.demo.ovh.image.scheduler.projection.model.FetchRegionIdsGroupedByNameQuery;
import com.example.demo.ovh.image.scheduler.projection.model.FetchRegionIdsGroupedByNameResponse;
import com.example.demo.ovh.image.scheduler.projection.projection.ImageProjection;
import com.example.demo.ovh.region.entity.QRegionEntity;
import com.google.common.collect.ImmutableMap;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ImageSchedulerProjectionService implements IImageSchedulerProjectionService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public ExistsImageByNameAndRegionNameResponse existsByNameAndRegionName(ExistsImageByNameAndRegionNameQuery query) {

        QImageEntity qImage = QImageEntity.imageEntity;
        QRegionEntity qRegion = QRegionEntity.regionEntity;

        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(qImage.name.eq(query.getName()));
        predicate.and(qRegion.name.eq(query.getRegionName()));

        long count = queryFactory.select(qImage.id)
                .from(qImage)
                .leftJoin(qImage.regionEntity, qRegion)
                .where(predicate)
                .fetchCount();

        return new ExistsImageByNameAndRegionNameResponse(count >= 1);
    }

    @Override
    @QueryHandler
    public FetchImageProjectionByNameAndRegionNameResponse fetchImageByNameAndRegionName(FetchImageProjectionByNameAndRegionNameQuery query) {

        QImageEntity qImage = QImageEntity.imageEntity;
        QRegionEntity qRegion = QRegionEntity.regionEntity;

        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(qImage.name.eq(query.getName()));
        predicate.and(qRegion.name.eq(query.getRegionName()));

        ImageProjection projection = queryFactory.select(Projections.constructor(
                    ImageProjection.class,
                    qImage.id,
                    qImage.ovhId,
                    qImage.name,
                    qImage.type,
                    qImage.imageCreatedDate,
                    qImage.flavorType,
                    qImage.hourly,
                    qImage.monthly,
                    qImage.size,
                    qImage.minRam,
                    qImage.minDisk,
                    qImage.username,
                    qImage.status,
                    qImage.visibility
                ))
                .from(qImage)
                .innerJoin(qImage.regionEntity, qRegion)
                .where(predicate)
                .fetchOne();

        return new FetchImageProjectionByNameAndRegionNameResponse(projection);
    }

    @Override
    @QueryHandler
    public FetchRegionIdsGroupedByNameResponse fetchRegionIdsGroupedByName(FetchRegionIdsGroupedByNameQuery query) {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        Map<String, String> regions = queryFactory.select(qRegion.name, qRegion.id)
                .from(qRegion)
                .transform(GroupBy.groupBy(qRegion.name).as(qRegion.id));

        return new FetchRegionIdsGroupedByNameResponse(ImmutableMap.copyOf(regions));
    }
}
