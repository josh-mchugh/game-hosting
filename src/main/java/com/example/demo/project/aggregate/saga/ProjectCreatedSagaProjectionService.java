package com.example.demo.project.aggregate.saga;

import com.example.demo.ovh.credential.entity.CredentialType;
import com.example.demo.ovh.credential.entity.QCredentialEntity;
import com.example.demo.project.aggregate.saga.model.FetchAnsibleCredentialQuery;
import com.example.demo.project.aggregate.saga.model.FetchAnsibleCredentialResponse;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectCreatedSagaProjectionService implements IProjectCreatedSagaProjectionService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public FetchAnsibleCredentialResponse fetchAnsibleCredential(FetchAnsibleCredentialQuery query) {

        QCredentialEntity qCredential = QCredentialEntity.credentialEntity;

        String ovhId = queryFactory.select(qCredential.ovhId)
                .from(qCredential)
                .where(qCredential.type.eq(CredentialType.ANSIBLE))
                .fetchOne();

        return new FetchAnsibleCredentialResponse(ovhId);
    }
}
