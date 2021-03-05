package com.example.demo.framework.seed.ovh.credential.projection;

import com.example.demo.framework.seed.ovh.credential.projection.model.ExistsAnyCredentialQuery;
import com.example.demo.framework.seed.ovh.credential.projection.model.ExistsAnyCredentialResponse;
import com.example.demo.ovh.credential.entity.QCredentialEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CredentialSeedProjectionService implements ICredentialSeedProjectionService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public ExistsAnyCredentialResponse existsAny(ExistsAnyCredentialQuery query) {

        QCredentialEntity qCredential = QCredentialEntity.credentialEntity;

        long count = queryFactory.select(qCredential.id)
                .from(qCredential)
                .fetchCount();

        return new ExistsAnyCredentialResponse(count >= 1);
    }
}
