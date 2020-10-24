package com.example.demo.ovh.flavor.projection;

import com.example.demo.ovh.flavor.entity.QFlavorEntity;
import com.example.demo.ovh.flavor.projection.model.FetchFlavorIdByFlavorIdQuery;
import com.example.demo.ovh.flavor.projection.model.FetchFlavorIdByFlavorIdResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlavorProjector implements IFlavorProjector {

    private final JPQLQueryFactory queryFactory;

    @Override
    public boolean existsAny() {

        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;

        long count = queryFactory.select(qFlavor.id)
                .from(qFlavor)
                .fetchCount();

        return count >= 1;
    }

    @Override
    public boolean existsByFlavorId(String flavorId) {

        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;

        long count = queryFactory.selectFrom(qFlavor)
                .where(qFlavor.flavorId.eq(flavorId))
                .fetchCount();

        return count >= 1;
    }

    @Override
    public FetchFlavorIdByFlavorIdResponse fetchFlavorIdByFlavorId(FetchFlavorIdByFlavorIdQuery query) {

        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;

        return queryFactory.select(Projections.constructor(
                    FetchFlavorIdByFlavorIdResponse.class,
                    qFlavor.id
                ))
                .from(qFlavor)
                .where(qFlavor.flavorId.eq(query.getFlavorId()))
                .fetchOne();
    }
}
