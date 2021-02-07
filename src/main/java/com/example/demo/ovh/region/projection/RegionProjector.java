package com.example.demo.ovh.region.projection;

import com.example.demo.ovh.region.entity.QRegionEntity;
import com.example.demo.ovh.region.entity.RegionEntity;
import com.example.demo.ovh.region.entity.mapper.RegionMapper;
import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.ovh.region.projection.model.AdminGameServerRegionProjection;
import com.example.demo.ovh.region.projection.model.FetchAdminGameServerRegionsQuery;
import com.example.demo.ovh.region.projection.model.FetchAdminGameServerRegionsResponse;
import com.example.demo.ovh.region.projection.model.FetchRegionByNameQuery;
import com.example.demo.ovh.region.projection.model.FetchRegionIdsGroupByNameProjection;
import com.google.common.collect.ImmutableMap;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public Region fetchRegionByName(FetchRegionByNameQuery query) {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        RegionEntity entity = queryFactory.select(qRegion)
                .from(qRegion)
                .where(qRegion.name.eq(query.getName()))
                .fetchOne();

        return RegionMapper.map(entity);
    }

    @Override
    public FetchRegionIdsGroupByNameProjection fetchRegionIdsGroupedByName() {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        Map<String, String> regionMap = queryFactory.select(qRegion.name, qRegion.id)
                .from(qRegion)
                .transform(GroupBy.groupBy(qRegion.name).as(qRegion.id));

        return new FetchRegionIdsGroupByNameProjection(ImmutableMap.copyOf(regionMap));
    }

    @Override
    public FetchAdminGameServerRegionsResponse fetchRegions(FetchAdminGameServerRegionsQuery query) {

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
}
