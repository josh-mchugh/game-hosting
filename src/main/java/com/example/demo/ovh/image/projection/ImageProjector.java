package com.example.demo.ovh.image.projection;

import com.example.demo.ovh.image.entity.QImageEntity;
import com.example.demo.ovh.image.projection.model.ExistImageNameAndRegionNameQuery;
import com.example.demo.ovh.image.projection.model.FetchImageAndRegionIdProjection;
import com.example.demo.ovh.image.projection.model.FetchImageIdAndRegionIdQuery;
import com.example.demo.ovh.region.entity.QRegionEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageProjector implements IImageProjector {

    private final JPQLQueryFactory queryFactory;

    @Override
    public boolean existsAny() {

        QImageEntity qImage = QImageEntity.imageEntity;

        long count = queryFactory.select(qImage.id)
                .from(qImage)
                .fetchCount();

        return count >= 1;
    }

    @Override
    public boolean existsByNameAndRegionName(ExistImageNameAndRegionNameQuery query) {

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

        return count >= 1;
    }

    @Override
    public FetchImageAndRegionIdProjection fetchImageIdAndRegionIdQuery(FetchImageIdAndRegionIdQuery query) {

        QImageEntity qImage = QImageEntity.imageEntity;
        QRegionEntity qRegion = QRegionEntity.regionEntity;

        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(qImage.name.eq(query.getImageName()));
        predicate.and(qRegion.name.eq(query.getRegionName()));

        return queryFactory.select(Projections.constructor(
                    FetchImageAndRegionIdProjection.class,
                    qImage.id,
                    qRegion.id
                )).from(qImage)
                .innerJoin(qImage.regionEntity, qRegion)
                .where(predicate)
                .fetchOne();
    }
}
