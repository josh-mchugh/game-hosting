package com.example.demo.awx.credential.service;

import com.example.demo.awx.credential.entity.AwxCredentialEntity;
import com.example.demo.awx.credential.entity.QAwxCredentialEntity;
import com.example.demo.awx.credential.mapper.AwxCredentialMapper;
import com.example.demo.awx.credential.model.AwxCredential;
import com.example.demo.awx.credential.service.model.AwxCredentialCreateRequest;
import com.example.demo.awx.organization.entity.AwxOrganizationEntity;
import com.example.demo.awx.organization.entity.QAwxOrganizationEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class AwxCredentialService implements IAwxCredentialService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;
    private final StringEncryptor encryptor;

    @Override
    public boolean existsAny() {

        QAwxCredentialEntity qAwxCredential = QAwxCredentialEntity.awxCredentialEntity;

        long count = queryFactory.select(qAwxCredential.id)
                .from(qAwxCredential)
                .fetchCount();

        return count >= 1;
    }

    @Override
    public AwxCredential getByName(String name) {

        QAwxCredentialEntity qAwxCredential = QAwxCredentialEntity.awxCredentialEntity;

        AwxCredentialEntity entity = queryFactory.select(qAwxCredential)
                .from(qAwxCredential)
                .where(qAwxCredential.name.eq(name))
                .fetchOne();

        return AwxCredentialMapper.map(entity);
    }

    @Override
    public AwxCredential handleAwxCredentialCreate(AwxCredentialCreateRequest request) {

        QAwxOrganizationEntity qAwxOrganization = QAwxOrganizationEntity.awxOrganizationEntity;

        AwxOrganizationEntity awxOrganizationEntity = queryFactory.select(qAwxOrganization)
                .from(qAwxOrganization)
                .where(qAwxOrganization.organizationId.eq(request.getOrganizationId()))
                .fetchOne();

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setAwxOrganizationEntity(awxOrganizationEntity);
        entity.setCredentialId(request.getCredentialId());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setPrivateKey(encryptor.encrypt(request.getPrivateKey()));
        entity.setPassphrase(encryptor.encrypt(request.getPassphrase()));
        entity.setType(request.getType());

        entityManager.persist(entity);

        return AwxCredentialMapper.map(entity);
    }
}
