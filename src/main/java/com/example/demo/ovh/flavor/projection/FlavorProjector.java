package com.example.demo.ovh.flavor.projection;

import com.example.demo.ovh.flavor.entity.FlavorEntity;
import com.example.demo.ovh.flavor.entity.QFlavorEntity;
import com.example.demo.ovh.flavor.entity.mapper.FlavorMapper;
import com.example.demo.ovh.flavor.entity.model.Flavor;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlavorProjector implements IFlavorProjector {

    private final JPQLQueryFactory queryFactory;

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
