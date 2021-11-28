package com.example.demo.ovh.flavor.scheduler.projection;

import com.example.demo.ovh.flavor.entity.QFlavorEntity;
import com.example.demo.ovh.flavor.scheduler.projection.model.ExistsFlavorByOvhIdQuery;
import com.example.demo.ovh.flavor.scheduler.projection.model.ExistsFlavorByOvhIdResponse;
import com.example.demo.ovh.flavor.scheduler.projection.model.FetchFlavorByOvhIdQuery;
import com.example.demo.ovh.flavor.scheduler.projection.model.FetchFlavorByOvhIdResponse;
import com.example.demo.ovh.flavor.scheduler.projection.model.FetchRegionIdsGroupedByNameQuery;
import com.example.demo.ovh.flavor.scheduler.projection.model.FetchRegionIdsGroupedByNameResponse;
import com.example.demo.ovh.flavor.scheduler.projection.projection.FlavorProjection;
import com.example.demo.ovh.region.entity.QRegionEntity;
import com.google.common.collect.ImmutableMap;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class FlavorSchedulerProjectionServiceImpl implements FlavorSchedulerProjectionService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public ExistsFlavorByOvhIdResponse existsByOvhId(ExistsFlavorByOvhIdQuery query) {

        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;

        long count = queryFactory.selectFrom(qFlavor)
                .where(qFlavor.ovhId.eq(query.getOvhId()))
                .fetchCount();

        return new ExistsFlavorByOvhIdResponse(count >= 1);
    }

    @Override
    @QueryHandler
    public FetchFlavorByOvhIdResponse fetchFlavorByOvhId(FetchFlavorByOvhIdQuery query) {

        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;

        FlavorProjection projection = queryFactory.select(Projections.constructor(
                    FlavorProjection.class,
                    qFlavor.id,
                    qFlavor.name,
                    qFlavor.type,
                    qFlavor.available,
                    qFlavor.hourly,
                    qFlavor.monthly,
                    qFlavor.quota,
                    qFlavor.osType,
                    qFlavor.vcpus,
                    qFlavor.ram,
                    qFlavor.disk,
                    qFlavor.inboundBandwidth,
                    qFlavor.outboundBandwidth
                ))
                .from(qFlavor)
                .where(qFlavor.ovhId.eq(query.getOvhId()))
                .fetchOne();

        return new FetchFlavorByOvhIdResponse(projection);
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
