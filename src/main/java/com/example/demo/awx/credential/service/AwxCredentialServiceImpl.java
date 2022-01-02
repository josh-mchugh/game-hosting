package com.example.demo.awx.credential.service;

import com.example.demo.awx.credential.entity.AwxCredentialEntity;
import com.example.demo.awx.credential.entity.mapper.AwxCredentialMapper;
import com.example.demo.awx.credential.entity.model.AwxCredential;
import com.example.demo.awx.credential.service.model.AwxCredentialCreateRequest;
import com.example.demo.awx.organization.entity.AwxOrganizationEntity;
import com.example.demo.awx.organization.entity.QAwxOrganizationEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class AwxCredentialServiceImpl implements AwxCredentialService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    @EventHandler
    public AwxCredential handleCreated(AwxCredentialCreateRequest request) {

        QAwxOrganizationEntity qAwxOrganization = QAwxOrganizationEntity.awxOrganizationEntity;

        AwxOrganizationEntity awxOrganizationEntity = queryFactory.select(qAwxOrganization)
                .from(qAwxOrganization)
                .where(qAwxOrganization.id.eq(request.getAwxOrganizationId().toString()))
                .fetchOne();

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setAwxOrganizationEntity(awxOrganizationEntity);
        entity.setAwxId(request.getAwxId());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setPrivateKey(request.getPrivateKey());
        entity.setPassphrase(request.getPassphrase());
        entity.setType(request.getType());

        entityManager.persist(entity);

        return AwxCredentialMapper.map(entity);
    }
}
