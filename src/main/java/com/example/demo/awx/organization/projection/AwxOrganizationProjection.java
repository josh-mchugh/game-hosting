package com.example.demo.awx.organization.projection;

import com.example.demo.awx.organization.entity.QAwxOrganizationEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AwxOrganizationProjection implements IAwxOrganizationProjection {

    private final JPQLQueryFactory queryFactory;

    @Override
    public boolean existsAny() {

        QAwxOrganizationEntity qAwxOrganization = QAwxOrganizationEntity.awxOrganizationEntity;

        long count = queryFactory.select(qAwxOrganization.id)
                .from(qAwxOrganization)
                .fetchCount();

        return count >= 1;
    }
}
