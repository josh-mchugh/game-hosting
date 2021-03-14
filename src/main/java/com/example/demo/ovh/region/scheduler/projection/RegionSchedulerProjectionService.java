package com.example.demo.ovh.region.scheduler.projection;

import com.example.demo.ovh.region.entity.QRegionEntity;
import com.example.demo.ovh.region.scheduler.projection.model.ExistsRegionByNameQuery;
import com.example.demo.ovh.region.scheduler.projection.model.ExistsRegionByNameResponse;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegionSchedulerProjectionService implements IRegionSchedulerProjectionService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public ExistsRegionByNameResponse existsByName(ExistsRegionByNameQuery query) {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        long count = queryFactory.select(qRegion.id)
                .from(qRegion)
                .where(qRegion.name.eq(query.getName()))
                .fetchCount();

        return new ExistsRegionByNameResponse(count >= 1);
    }
}
