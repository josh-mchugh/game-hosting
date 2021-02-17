package com.example.demo.ovh.image.projection;

import com.example.demo.ovh.image.entity.ImageEntity;
import com.example.demo.ovh.image.entity.QImageEntity;
import com.example.demo.ovh.image.entity.mapper.ImageMapper;
import com.example.demo.ovh.image.entity.model.Image;
import com.example.demo.ovh.image.projection.model.ExistByNameAndRegionNameQuery;
import com.example.demo.ovh.image.projection.model.FetchImageByNameAndRegionNameQuery;
import com.example.demo.ovh.region.entity.QRegionEntity;
import com.querydsl.core.BooleanBuilder;
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
    public boolean existsByNameAndRegionName(ExistByNameAndRegionNameQuery query) {

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
    public Image fetchImageByNameAndRegionName(FetchImageByNameAndRegionNameQuery query) {

        QImageEntity qImage = QImageEntity.imageEntity;
        QRegionEntity qRegion = QRegionEntity.regionEntity;

        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(qImage.name.eq(query.getName()));
        predicate.and(qRegion.name.eq(query.getRegionName()));

        ImageEntity entity = queryFactory.select(qImage)
                .from(qImage)
                .innerJoin(qImage.regionEntity, qRegion)
                .where(predicate)
                .fetchOne();

        return ImageMapper.map(entity);
    }
}
