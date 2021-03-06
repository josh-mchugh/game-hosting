package com.example.demo.framework.seed.ovh.flavor.projection;

import com.example.demo.framework.seed.ovh.flavor.projection.model.ExistsAnyFlavorQuery;
import com.example.demo.framework.seed.ovh.flavor.projection.model.ExistsAnyFlavorResponse;
import com.example.demo.ovh.flavor.entity.QFlavorEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

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
}
