package com.example.demo.ovh.credential.service;

import com.example.demo.ovh.credential.entity.CredentialEntity;
import com.example.demo.ovh.credential.entity.CredentialType;
import com.example.demo.ovh.credential.entity.QCredentialEntity;
import com.example.demo.ovh.credential.mapper.CredentialMapper;
import com.example.demo.ovh.credential.model.Credential;
import com.example.demo.ovh.credential.service.model.CredentialCreateRequest;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class CredentialService implements ICredentialService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;
    private final StringEncryptor encryptor;

    @Override
    public boolean existsAny() {

        QCredentialEntity qCredential = QCredentialEntity.credentialEntity;

        long count = queryFactory.select(qCredential.id)
                .from(qCredential)
                .fetchCount();

        return count >= 1;
    }

    @Override
    public String getAnsibleCredentialSshKeyId() {

        QCredentialEntity qCredential = QCredentialEntity.credentialEntity;

        return queryFactory.select(qCredential.sshKeyId)
                .from(qCredential)
                .where(qCredential.type.eq(CredentialType.ANSIBLE))
                .fetchOne();
    }

    @Override
    public Credential handleSshKeyCreate(CredentialCreateRequest request) {

        CredentialEntity entity = new CredentialEntity();
        entity.setSshKeyId(request.getSshKeyId());;
        entity.setName(request.getName());
        entity.setPublicKey(encryptor.encrypt(request.getPublicKey()));
        entity.setType(request.getType());

        entityManager.persist(entity);

        return CredentialMapper.map(entity);
    }
}
