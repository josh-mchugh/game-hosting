package com.example.demo.ovh.instance.projection;

import com.example.demo.ovh.instance.entity.QInstanceEntity;
import com.example.demo.ovh.instance.entity.mapper.InstanceMapper;
import com.example.demo.ovh.instance.entity.model.Instance;
import com.example.demo.ovh.instance.projection.model.FetchInstanceByOvhIdsProjection;
import com.example.demo.ovh.instance.projection.model.FetchInstanceDetailsByProjectIdProjection;
import com.example.demo.ovh.instance.projection.model.FetchInstanceDetailsByProjectIdQuery;
import com.example.demo.ovh.instance.projection.model.FetchInstancesByOvhIdsQuery;
import com.google.common.collect.ImmutableList;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstanceProjector implements IInstanceProjector {

    private final JPQLQueryFactory queryFactory;

    @Override
    public FetchInstanceByOvhIdsProjection fetchInstancesByIds(FetchInstancesByOvhIdsQuery query) {

        QInstanceEntity qInstance = QInstanceEntity.instanceEntity;

        ImmutableList<Instance> instances = queryFactory.select(qInstance)
                .from(qInstance)
                .where(qInstance.ovhId.in(query.getOvhIds()))
                .fetch()
                .stream()
                .map(InstanceMapper::map)
                .collect(ImmutableList.toImmutableList());

        return new FetchInstanceByOvhIdsProjection(instances);
    }

    @Override
    public FetchInstanceDetailsByProjectIdProjection fetchInstanceDetails(FetchInstanceDetailsByProjectIdQuery query) {

        QInstanceEntity qInstance = QInstanceEntity.instanceEntity;

        return queryFactory.select(
                Projections.constructor(FetchInstanceDetailsByProjectIdProjection.class,
                        qInstance.ovhId,
                        qInstance.status,
                        qInstance.ip4Address
                ))
                .from(qInstance)
                .where(qInstance.instanceGroupEntity.projectEntity.id.eq(query.getProjectId()))
                .fetchOne();
    }
}
