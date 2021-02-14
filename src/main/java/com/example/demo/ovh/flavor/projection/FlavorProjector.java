package com.example.demo.ovh.flavor.projection;

import com.example.demo.ovh.flavor.entity.FlavorEntity;
import com.example.demo.ovh.flavor.entity.QFlavorEntity;
import com.example.demo.ovh.flavor.entity.mapper.FlavorMapper;
import com.example.demo.ovh.flavor.entity.model.Flavor;
import com.example.demo.ovh.flavor.projection.model.AdminGameServerFlavorProjection;
import com.example.demo.ovh.flavor.projection.model.FetchAdminGameServerFlavorsQuery;
import com.example.demo.ovh.flavor.projection.model.FetchAdminGameServerFlavorsResponse;
import com.example.demo.ovh.flavor.projection.model.FetchFlavorIdByOvhIdProjection;
import com.example.demo.ovh.flavor.projection.model.FetchFlavorIdByOvhIdQuery;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public Flavor fetchFlavorByOvhId(String ovhId) {

        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;

        FlavorEntity entity = queryFactory.select(qFlavor)
                .from(qFlavor)
                .where(qFlavor.ovhId.eq(ovhId))
                .fetchOne();

        return FlavorMapper.map(entity);
    }

    @Override
    public FetchAdminGameServerFlavorsResponse fetchFlavorsByRegionId(FetchAdminGameServerFlavorsQuery query) {

        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;

        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(qFlavor.regionEntity.id.eq(query.getRegionId().toString()));
        predicate.and(qFlavor.type.eq("ovh.vps-ssd"));

        if (StringUtils.isNotBlank(query.getSearch())) {

            predicate.and(qFlavor.name.containsIgnoreCase(query.getSearch()));
        }

        List<AdminGameServerFlavorProjection> projections = queryFactory.select(
                    Projections.constructor(
                        AdminGameServerFlavorProjection.class,
                        qFlavor.id,
                        qFlavor.name,
                        qFlavor.vcpus,
                        qFlavor.ram
                ))
                .from(qFlavor)
                .where(predicate)
                .orderBy(qFlavor.name.asc())
                .fetch();

        return new FetchAdminGameServerFlavorsResponse(projections);
    }
}
