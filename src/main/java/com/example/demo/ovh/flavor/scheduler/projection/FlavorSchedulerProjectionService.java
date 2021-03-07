package com.example.demo.ovh.flavor.scheduler.projection;

import com.example.demo.ovh.flavor.entity.QFlavorEntity;
import com.example.demo.ovh.flavor.scheduler.projection.model.ExistsFlavorByOvhIdQuery;
import com.example.demo.ovh.flavor.scheduler.projection.model.ExistsFlavorByOvhIdResponse;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlavorSchedulerProjectionService implements IFlavorSchedulerProjectionService {

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
}
