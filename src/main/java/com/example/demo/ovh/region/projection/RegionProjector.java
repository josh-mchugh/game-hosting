package com.example.demo.ovh.region.projection;

import com.example.demo.ovh.region.entity.QRegionEntity;
import com.example.demo.ovh.region.projection.model.FetchRegionIdsGroupByNameProjection;
import com.google.common.collect.ImmutableMap;
import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class RegionProjector implements IRegionProjector {

    private final JPQLQueryFactory queryFactory;

    @Override
    public FetchRegionIdsGroupByNameProjection fetchRegionIdsGroupedByName() {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        Map<String, String> regionMap = queryFactory.select(qRegion.name, qRegion.id)
                .from(qRegion)
                .transform(GroupBy.groupBy(qRegion.name).as(qRegion.id));

        return new FetchRegionIdsGroupByNameProjection(ImmutableMap.copyOf(regionMap));
    }
}
