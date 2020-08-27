package com.example.demo.awx.project.service;

import com.example.demo.awx.credential.entity.AwxCredentialEntity;
import com.example.demo.awx.credential.entity.QAwxCredentialEntity;
import com.example.demo.awx.organization.entity.AwxOrganizationEntity;
import com.example.demo.awx.organization.entity.QAwxOrganizationEntity;
import com.example.demo.awx.project.entity.AwxProjectEntity;
import com.example.demo.awx.project.entity.QAwxProjectEntity;
import com.example.demo.awx.project.mapper.AwxProjectMapper;
import com.example.demo.awx.project.model.AwxProject;
import com.example.demo.awx.project.service.model.AwxProjectCreateRequest;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class AwxProjectService implements IAwxProjectService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;
    private final StringEncryptor stringEncryptor;

    @Override
    public boolean existsAny() {

        QAwxProjectEntity qAwxProject = QAwxProjectEntity.awxProjectEntity;

        long count = queryFactory.select(qAwxProject)
                .from(qAwxProject)
                .fetchCount();

        return count >= 1;
    }

    @Override
    public AwxProject getByName(String name) {

        QAwxProjectEntity qAwxProject = QAwxProjectEntity.awxProjectEntity;

        AwxProjectEntity entity = queryFactory.select(qAwxProject)
                .from(qAwxProject)
                .where(qAwxProject.name.eq(name))
                .fetchOne();

        return AwxProjectMapper.map(entity);
    }

    @Override
    public AwxProject handleCreateRequest(AwxProjectCreateRequest request) {

        QAwxOrganizationEntity qAwxOrganization = QAwxOrganizationEntity.awxOrganizationEntity;
        QAwxCredentialEntity qAwxCredential = QAwxCredentialEntity.awxCredentialEntity;

        AwxCredentialEntity awxCredentialEntity = queryFactory.select(qAwxCredential)
                .from(qAwxCredential)
                .where(qAwxCredential.credentialId.eq(request.getCredentialId()))
                .fetchOne();

        AwxOrganizationEntity awxOrganizationEntity = queryFactory.select(qAwxOrganization)
                .from(qAwxOrganization)
                .where(qAwxOrganization.organizationId.eq(request.getOrganizationId()))
                .fetchOne();

        AwxProjectEntity entity = new AwxProjectEntity();
        entity.setAwxCredentialEntity(awxCredentialEntity);
        entity.setAwxOrganizationEntity(awxOrganizationEntity);
        entity.setProjectId(request.getProjectId());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setScmType(stringEncryptor.encrypt(request.getScmType()));
        entity.setScmUrl(stringEncryptor.encrypt(request.getScmUrl()));
        entity.setScmBranch(stringEncryptor.encrypt(request.getScmBranch()));

        entityManager.persist(entity);

        return AwxProjectMapper.map(entity);
    }
}
