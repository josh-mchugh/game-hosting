package com.example.demo.awx.credential.entity.service;

import com.example.demo.awx.credential.aggregate.event.AwxCredentialCreatedEvent;
import com.example.demo.awx.credential.entity.AwxCredentialEntity;
import com.example.demo.awx.credential.entity.mapper.AwxCredentialMapper;
import com.example.demo.awx.credential.entity.model.AwxCredential;
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
    public AwxCredential handleCreated(AwxCredentialCreatedEvent event) {

        QAwxOrganizationEntity qAwxOrganization = QAwxOrganizationEntity.awxOrganizationEntity;

        AwxOrganizationEntity awxOrganizationEntity = queryFactory.select(qAwxOrganization)
                .from(qAwxOrganization)
                .where(qAwxOrganization.id.eq(event.getAwxOrganizationId().toString()))
                .fetchOne();

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setId(event.getId());
        entity.setAwxOrganizationEntity(awxOrganizationEntity);
        entity.setAwxId(event.getAwxId());
        entity.setName(event.getName());
        entity.setDescription(event.getDescription());
        entity.setPrivateKey(event.getPrivateKey());
        entity.setPassphrase(event.getPassphrase());
        entity.setType(event.getType());

        entityManager.persist(entity);

        return AwxCredentialMapper.map(entity);
    }
}
