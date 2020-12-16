package com.example.demo.ovh.credential.projector;

import com.example.demo.ovh.credential.entity.CredentialType;
import com.example.demo.ovh.credential.entity.QCredentialEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CredentialProjector implements ICredentialProjector {

    private final JPQLQueryFactory queryFactory;

    @Override
    public boolean existsAny() {

        QCredentialEntity qCredential = QCredentialEntity.credentialEntity;

        long count = queryFactory.select(qCredential.id)
                .from(qCredential)
                .fetchCount();

        return count >= 1;
    }

    @Override
    public String getAnsibleOvhId() {

        QCredentialEntity qCredential = QCredentialEntity.credentialEntity;

        return queryFactory.select(qCredential.ovhId)
                .from(qCredential)
                .where(qCredential.type.eq(CredentialType.ANSIBLE))
                .fetchOne();
    }
}
