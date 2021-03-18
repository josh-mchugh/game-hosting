package com.example.demo.framework.seed.ovh.flavor.projection;

import com.example.demo.framework.seed.ovh.flavor.projection.model.ExistsAnyFlavorQuery;
import com.example.demo.framework.seed.ovh.flavor.projection.model.ExistsAnyFlavorResponse;
import com.example.demo.framework.seed.ovh.flavor.projection.model.FetchRegionIdsGroupedByNameQuery;
import com.example.demo.framework.seed.ovh.flavor.projection.model.FetchRegionIdsGroupedByNameResponse;
import com.example.demo.ovh.flavor.entity.QFlavorEntity;
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
public class FlavorSeedProjectionService implements IFlavorSeedProjectionService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public ExistsAnyFlavorResponse existsAny(ExistsAnyFlavorQuery query) {

        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;

        long count = queryFactory.select(qFlavor.id)
                .from(qFlavor)
                .fetchCount();

        return new ExistsAnyFlavorResponse(count >= 1);
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
