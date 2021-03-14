package com.example.demo.ovh.instance.scheduler.projection;

import com.example.demo.ovh.instance.entity.QInstanceEntity;
import com.example.demo.ovh.instance.scheduler.projection.model.FetchInstancesByOvhIdsQuery;
import com.example.demo.ovh.instance.scheduler.projection.model.FetchInstancesByOvhIdsResponse;
import com.example.demo.ovh.instance.scheduler.projection.projection.InstanceProjection;
import com.google.common.collect.ImmutableList;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InstanceSchedulerProjectionService implements IInstanceSchedulerProjectionService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public FetchInstancesByOvhIdsResponse fetchInstanceByOvhId(FetchInstancesByOvhIdsQuery query) {

        QInstanceEntity qInstance = QInstanceEntity.instanceEntity;

        List<InstanceProjection> projections = queryFactory.select(Projections.constructor(
                    InstanceProjection.class,
                    qInstance.id,
                    qInstance.ovhId,
                    qInstance.name,
                    qInstance.instanceCreatedDate,
                    qInstance.status,
                    qInstance.ip4Address,
                    qInstance.ip6Address
                ))
                .from(qInstance)
                .where(qInstance.ovhId.in(query.getOvhIds()))
                .fetch();

        return new FetchInstancesByOvhIdsResponse(ImmutableList.copyOf(projections));
    }
}
