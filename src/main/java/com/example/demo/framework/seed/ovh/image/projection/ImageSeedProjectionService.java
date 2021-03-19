package com.example.demo.framework.seed.ovh.image.projection;

import com.example.demo.framework.seed.ovh.image.projection.model.ExistsAnyImageQuery;
import com.example.demo.framework.seed.ovh.image.projection.model.ExistsAnyImageResponse;
import com.example.demo.framework.seed.ovh.image.projection.model.FetchRegionIdsGroupedByNameQuery;
import com.example.demo.framework.seed.ovh.image.projection.model.FetchRegionIdsGroupedByNameResponse;
import com.example.demo.ovh.image.entity.QImageEntity;
import com.example.demo.ovh.region.entity.QRegionEntity;
import com.google.common.collect.ImmutableMap;
import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ImageSeedProjectionService implements IImageSeedProjectionService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public ExistsAnyImageResponse existsAny(ExistsAnyImageQuery query) {

        QImageEntity qImage = QImageEntity.imageEntity;

        long count = queryFactory.select(qImage.id)
                .from(qImage)
                .fetchCount();

        return new ExistsAnyImageResponse(count >= 1);
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
