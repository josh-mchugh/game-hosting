package com.example.demo.awx.project.entity.service;

import com.example.demo.awx.credential.entity.AwxCredentialEntity;
import com.example.demo.awx.credential.entity.QAwxCredentialEntity;
import com.example.demo.awx.organization.entity.AwxOrganizationEntity;
import com.example.demo.awx.organization.entity.QAwxOrganizationEntity;
import com.example.demo.awx.project.aggregate.event.AwxProjectCreatedEvent;
import com.example.demo.awx.project.entity.AwxProjectEntity;
import com.example.demo.awx.project.entity.mapper.AwxProjectMapper;
import com.example.demo.awx.project.entity.model.AwxProject;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class AwxProjectService implements IAwxProjectService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    @EventHandler
    public AwxProject handleCreated(AwxProjectCreatedEvent event) {

        QAwxOrganizationEntity qAwxOrganization = QAwxOrganizationEntity.awxOrganizationEntity;
        QAwxCredentialEntity qAwxCredential = QAwxCredentialEntity.awxCredentialEntity;

        AwxCredentialEntity awxCredentialEntity = queryFactory.select(qAwxCredential)
                .from(qAwxCredential)
                .where(qAwxCredential.id.eq(event.getAwxCredentialId()))
                .fetchOne();

        AwxOrganizationEntity awxOrganizationEntity = queryFactory.select(qAwxOrganization)
                .from(qAwxOrganization)
                .where(qAwxOrganization.organizationId.eq(event.getOrganizationId()))
                .fetchOne();

        AwxProjectEntity entity = new AwxProjectEntity();
        entity.setId(event.getId());
        entity.setAwxCredentialEntity(awxCredentialEntity);
        entity.setAwxOrganizationEntity(awxOrganizationEntity);
        entity.setProjectId(event.getProjectId());
        entity.setName(event.getName());
        entity.setDescription(event.getDescription());
        entity.setScmType(event.getScmType());
        entity.setScmUrl(event.getScmUrl());
        entity.setScmBranch(event.getScmBranch());

        entityManager.persist(entity);

        return AwxProjectMapper.map(entity);
    }
}
