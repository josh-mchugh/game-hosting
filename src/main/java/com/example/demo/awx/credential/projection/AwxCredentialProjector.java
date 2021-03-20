package com.example.demo.awx.credential.projection;

import com.example.demo.awx.credential.entity.AwxCredentialEntity;
import com.example.demo.awx.credential.entity.QAwxCredentialEntity;
import com.example.demo.awx.credential.entity.mapper.AwxCredentialMapper;
import com.example.demo.awx.credential.entity.model.AwxCredential;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AwxCredentialProjector implements IAwxCredentialProjector {

    private final JPQLQueryFactory queryFactory;

    @Override
    public AwxCredential getByName(String name) {

        QAwxCredentialEntity qAwxCredential = QAwxCredentialEntity.awxCredentialEntity;

        AwxCredentialEntity entity = queryFactory.select(qAwxCredential)
                .from(qAwxCredential)
                .where(qAwxCredential.name.eq(name))
                .fetchOne();

        return AwxCredentialMapper.map(entity);
    }
}
