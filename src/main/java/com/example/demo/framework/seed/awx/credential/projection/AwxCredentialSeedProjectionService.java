package com.example.demo.framework.seed.awx.credential.projection;

import com.example.demo.awx.credential.entity.QAwxCredentialEntity;
import com.example.demo.framework.seed.awx.credential.projection.model.ExistsAnyAwxCredentialQuery;
import com.example.demo.framework.seed.awx.credential.projection.model.ExistsAnyAwxCredentialResponse;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AwxCredentialSeedProjectionService implements IAwxCredentialSeedProjectionService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public ExistsAnyAwxCredentialResponse existsAny(ExistsAnyAwxCredentialQuery query) {

        QAwxCredentialEntity qAwxCredential = QAwxCredentialEntity.awxCredentialEntity;

        long count = queryFactory.select(qAwxCredential.id)
                .from(qAwxCredential)
                .fetchCount();

        return new ExistsAnyAwxCredentialResponse(count >= 1);
    }
}
