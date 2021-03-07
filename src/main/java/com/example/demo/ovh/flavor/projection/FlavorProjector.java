package com.example.demo.ovh.flavor.projection;

import com.example.demo.ovh.flavor.entity.FlavorEntity;
import com.example.demo.ovh.flavor.entity.QFlavorEntity;
import com.example.demo.ovh.flavor.entity.mapper.FlavorMapper;
import com.example.demo.ovh.flavor.entity.model.Flavor;
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

    @Override
    public Flavor fetchFlavorByOvhId(String ovhId) {

        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;

        FlavorEntity entity = queryFactory.select(qFlavor)
                .from(qFlavor)
                .where(qFlavor.ovhId.eq(ovhId))
                .fetchOne();

        return FlavorMapper.map(entity);
    }
}
