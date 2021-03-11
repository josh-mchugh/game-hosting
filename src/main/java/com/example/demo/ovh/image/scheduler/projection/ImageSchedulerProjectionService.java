package com.example.demo.ovh.image.scheduler.projection;

import com.example.demo.ovh.image.entity.QImageEntity;
import com.example.demo.ovh.image.scheduler.projection.model.ExistsImageByNameAndRegionNameQuery;
import com.example.demo.ovh.image.scheduler.projection.model.ExistsImageByNameAndRegionNameResponse;
import com.example.demo.ovh.region.entity.QRegionEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

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
}
