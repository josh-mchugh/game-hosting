package com.example.demo.awx.host.projection;

import com.example.demo.awx.host.entity.QAwxHostEntity;
import com.example.demo.awx.host.projection.model.AwxHostAwxIdProjection;
import com.example.demo.awx.host.projection.model.AwxHostAwxIdQuery;
import com.example.demo.ovh.instance.entity.QInstanceEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AwxHostProjector implements IAwxHostProjector {

    private final JPQLQueryFactory queryFactory;

    @Override
    public boolean existsAny() {

        QAwxHostEntity qAwxHost = QAwxHostEntity.awxHostEntity;

        long count = queryFactory.select(qAwxHost)
                .from(qAwxHost)
                .fetchCount();

        return count >= 1;
    }

    @Override
    public AwxHostAwxIdProjection getHostIdProjection(AwxHostAwxIdQuery query) {

        QAwxHostEntity qAwxHost = QAwxHostEntity.awxHostEntity;
        QInstanceEntity qInstance = QInstanceEntity.instanceEntity;

        return queryFactory.select(Projections.constructor(AwxHostAwxIdProjection.class,
                    qAwxHost.id,
                    qAwxHost.awxId
                ))
                .from(qAwxHost)
                .innerJoin(qAwxHost.instanceEntity, qInstance)
                .where(qInstance.instanceId.eq(query.getInstanceId()))
                .fetchOne();
    }
}
