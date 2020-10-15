package com.example.demo.ovh.region.projection;

import com.example.demo.ovh.region.entity.QRegionEntity;
import com.example.demo.ovh.region.projection.model.FetchIdByNameQuery;
import com.example.demo.ovh.region.projection.model.FetchIdByNameResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegionProjection implements IRegionProjection {

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
    public FetchIdByNameResponse fetchIdByName(FetchIdByNameQuery query) {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        return queryFactory.select(Projections.constructor(
                    FetchIdByNameResponse.class,
                    qRegion.id
                ))
                .from(qRegion)
                .where(qRegion.name.eq(query.getName()))
                .fetchOne();
    }
}