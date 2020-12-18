package com.example.demo.ovh.flavor.projection;

import com.example.demo.ovh.flavor.entity.QFlavorEntity;
import com.example.demo.ovh.flavor.projection.model.FetchFlavorIdByOvhIdProjection;
import com.example.demo.ovh.flavor.projection.model.FetchFlavorIdByOvhIdQuery;
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
    public boolean existsByOvhId(String ovhId) {

        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;

        long count = queryFactory.selectFrom(qFlavor)
                .where(qFlavor.ovhId.eq(ovhId))
                .fetchCount();

        return count >= 1;
    }

    @Override
    public FetchFlavorIdByOvhIdProjection fetchFlavorIdByOvhId(FetchFlavorIdByOvhIdQuery query) {

        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;

        return queryFactory.select(Projections.constructor(
                    FetchFlavorIdByOvhIdProjection.class,
                    qFlavor.id
                ))
                .from(qFlavor)
                .where(qFlavor.ovhId.eq(query.getOvhId()))
                .fetchOne();
    }
}
