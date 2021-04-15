package com.example.demo.awx.organization.projection;

import com.example.demo.awx.organization.entity.QAwxOrganizationEntity;
import com.example.demo.awx.organization.projection.model.FetchAwxOrganizationIdByAwxIdQuery;
import com.example.demo.awx.organization.projection.model.FetchAwxOrganizationIdByAwxIdResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AwxOrganizationProjector implements IAwxOrganizationProjection {

    private final JPQLQueryFactory queryFactory;

    @Override
    public FetchAwxOrganizationIdByAwxIdResponse fetchAwxOrganizationIdByAwxId(FetchAwxOrganizationIdByAwxIdQuery query) {

        QAwxOrganizationEntity qAwxOrganization = QAwxOrganizationEntity.awxOrganizationEntity;

        return queryFactory.select(Projections.constructor(
                    FetchAwxOrganizationIdByAwxIdResponse.class,
                    qAwxOrganization.id
                ))
                .from(qAwxOrganization)
                .where(qAwxOrganization.awxId.eq(query.getAwxId()))
                .fetchOne();
    }
}
