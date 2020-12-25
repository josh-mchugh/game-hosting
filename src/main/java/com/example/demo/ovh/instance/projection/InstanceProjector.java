package com.example.demo.ovh.instance.projection;

import com.example.demo.ovh.instance.entity.QInstanceEntity;
import com.example.demo.ovh.instance.entity.mapper.InstanceMapper;
import com.example.demo.ovh.instance.entity.model.Instance;
import com.example.demo.ovh.instance.projection.model.FetchInstanceByOvhIdsProjection;
import com.example.demo.ovh.instance.projection.model.FetchInstancesByOvhIdsQuery;
import com.google.common.collect.ImmutableList;
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
}
