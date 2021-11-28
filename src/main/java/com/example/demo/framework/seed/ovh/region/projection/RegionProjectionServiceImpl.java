package com.example.demo.framework.seed.ovh.region.projection;

import com.example.demo.framework.seed.ovh.region.projection.model.ExistsAnyRegionQuery;
import com.example.demo.framework.seed.ovh.region.projection.model.ExistsAnyRegionResponse;
import com.example.demo.ovh.region.entity.QRegionEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegionProjectionServiceImpl implements RegionSeedProjectionService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public ExistsAnyRegionResponse existsAny(ExistsAnyRegionQuery query) {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        long count = queryFactory.select(qRegion.id)
                .from(qRegion)
                .fetchCount();

        return new ExistsAnyRegionResponse(count >= 1);
    }
}
