package com.example.demo.framework.seed.awx.project.projection;

import com.example.demo.awx.credential.entity.QAwxCredentialEntity;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxCredentialByNameQuery;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxCredentialByNameResponse;
import com.example.demo.framework.seed.awx.project.projection.projection.AwxCredentialProjection;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AwxProjectSeedProjectionService implements IAwxProjectSeedProjectionService {

    private final JPQLQueryFactory queryFactory;

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
}
