package com.example.demo.ovh.region.projection;

import com.example.demo.ovh.region.entity.QRegionEntity;
import com.example.demo.ovh.region.projection.model.FetchRegionIdByNameProjection;
import com.example.demo.ovh.region.projection.model.FetchRegionIdByNameQuery;
import com.example.demo.ovh.region.projection.model.FetchRegionIdsGroupByNameProjection;
import com.google.common.collect.ImmutableMap;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class RegionProjector implements IRegionProjector {

    private final JPQLQueryFactory queryFactory;

    @Override
    public boolean existsAny() {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        long count = queryFactory.select(qRegion.id)
                .from(qRegion)
                .fetchCount();

        return count >= 1;
    }

    @Override
    public boolean existsByName(String name) {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        long count = queryFactory.select(qRegion.id)
                .from(qRegion)
                .where(qRegion.name.eq(name))
                .fetchCount();

        return count >= 1;
    }

    @Override
    public FetchRegionIdByNameProjection fetchIdByName(FetchRegionIdByNameQuery query) {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        return queryFactory.select(Projections.constructor(
                    FetchRegionIdByNameProjection.class,
                    qRegion.id
                ))
                .from(qRegion)
                .where(qRegion.name.eq(query.getName()))
                .fetchOne();
    }

    @Override
    public FetchRegionIdsGroupByNameProjection fetchRegionIdsGroupedByName() {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        Map<String, String> regionMap = queryFactory.select(qRegion.name, qRegion.id)
                .from(qRegion)
                .transform(GroupBy.groupBy(qRegion.name).as(qRegion.id));

        return new FetchRegionIdsGroupByNameProjection(ImmutableMap.copyOf(regionMap));
    }
}
