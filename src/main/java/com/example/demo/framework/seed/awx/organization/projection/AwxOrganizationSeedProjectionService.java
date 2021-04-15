package com.example.demo.framework.seed.awx.organization.projection;

import com.example.demo.awx.organization.entity.QAwxOrganizationEntity;
import com.example.demo.framework.seed.awx.organization.projection.model.ExistsAnyAwxOrganizationQuery;
import com.example.demo.framework.seed.awx.organization.projection.model.ExistsAnyAwxOrganizationResponse;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AwxOrganizationSeedProjectionService implements IAwxOrganizationSeedProjectionService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public ExistsAnyAwxOrganizationResponse existsAny(ExistsAnyAwxOrganizationQuery query) {

        QAwxOrganizationEntity qAwxOrganization = QAwxOrganizationEntity.awxOrganizationEntity;

        long count = queryFactory.select(qAwxOrganization.id)
                .from(qAwxOrganization)
                .fetchCount();

        return new ExistsAnyAwxOrganizationResponse(count >= 1);
    }
}
