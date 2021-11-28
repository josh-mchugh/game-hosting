package com.example.demo.framework.seed.awx.project.projection;

import com.example.demo.awx.credential.entity.QAwxCredentialEntity;
import com.example.demo.awx.organization.entity.QAwxOrganizationEntity;
import com.example.demo.awx.project.entity.QAwxProjectEntity;
import com.example.demo.framework.seed.awx.project.projection.model.ExistsAnyAwxProjectQuery;
import com.example.demo.framework.seed.awx.project.projection.model.ExistsAnyAwxProjectResponse;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxCredentialByNameQuery;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxCredentialByNameResponse;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxOrganizationIdByAwxIdQuery;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxOrganizationIdByAwxIdResponse;
import com.example.demo.framework.seed.awx.project.projection.projection.AwxCredentialProjection;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AwxProjectSeedProjectionServiceImpl implements AwxProjectSeedProjectionService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public ExistsAnyAwxProjectResponse existsAny(ExistsAnyAwxProjectQuery query) {

        QAwxProjectEntity qAwxProject = QAwxProjectEntity.awxProjectEntity;

        long count = queryFactory.select(qAwxProject.id)
                .from(qAwxProject)
                .fetchCount();

        return new ExistsAnyAwxProjectResponse(count >= 1);
    }

    @Override
    @QueryHandler
    public FetchAwxCredentialByNameResponse fetchAwxCredentialIdByName(FetchAwxCredentialByNameQuery query) {

        QAwxCredentialEntity qAwxCredential = QAwxCredentialEntity.awxCredentialEntity;

        AwxCredentialProjection projection = queryFactory.select(Projections.constructor(
                    AwxCredentialProjection.class,
                    qAwxCredential.id,
                    qAwxCredential.awxId
                ))
                .from(qAwxCredential)
                .where(qAwxCredential.name.eq(query.getName()))
                .fetchOne();

        return new FetchAwxCredentialByNameResponse(projection);
    }

    @Override
    @QueryHandler
    public FetchAwxOrganizationIdByAwxIdResponse fetchAwxOrganizationIdByAwxId(FetchAwxOrganizationIdByAwxIdQuery query) {

        QAwxOrganizationEntity qAwxOrganization = QAwxOrganizationEntity.awxOrganizationEntity;

        String id = queryFactory.select(qAwxOrganization.id)
                .from(qAwxOrganization)
                .where(qAwxOrganization.awxId.eq(query.getAwxId()))
                .fetchOne();

        return new FetchAwxOrganizationIdByAwxIdResponse(id != null ? UUID.fromString(id) : null);
    }
}
