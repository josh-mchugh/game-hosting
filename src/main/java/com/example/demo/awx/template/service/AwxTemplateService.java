package com.example.demo.awx.template.service;

import com.example.demo.awx.credential.entity.AwxCredentialEntity;
import com.example.demo.awx.credential.entity.QAwxCredentialEntity;
import com.example.demo.awx.inventory.entity.AwxInventoryEntity;
import com.example.demo.awx.inventory.entity.QAwxInventoryEntity;
import com.example.demo.awx.playbook.entity.AwxPlayBookEntity;
import com.example.demo.awx.playbook.entity.QAwxPlayBookEntity;
import com.example.demo.awx.template.entity.AwxTemplateEntity;
import com.example.demo.awx.template.entity.QAwxTemplateEntity;
import com.example.demo.awx.template.mapper.AwxTemplateMapper;
import com.example.demo.awx.template.model.AwxTemplate;
import com.example.demo.awx.template.service.model.AwxTemplateCreateRequest;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class AwxTemplateService implements IAwxTemplateService{

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    public boolean existsAny() {

        QAwxTemplateEntity qAwxTemplate = QAwxTemplateEntity.awxTemplateEntity;

        long count = queryFactory.select(qAwxTemplate)
                .from(qAwxTemplate)
                .fetchCount();

        return count >= 1;
    }

    @Override
    public AwxTemplate handleCreateRequest(AwxTemplateCreateRequest request) {

        QAwxCredentialEntity qAwxCredential = QAwxCredentialEntity.awxCredentialEntity;
        QAwxInventoryEntity qAwxInventory = QAwxInventoryEntity.awxInventoryEntity;
        QAwxPlayBookEntity qAwxPlayBook = QAwxPlayBookEntity.awxPlayBookEntity;

        AwxCredentialEntity awxCredentialEntity = queryFactory.select(qAwxCredential)
                .from(qAwxCredential)
                .where(qAwxCredential.credentialId.eq(request.getCredentialId()))
                .fetchOne();

        AwxInventoryEntity awxInventory = queryFactory.select(qAwxInventory)
                .from(qAwxInventory)
                .where(qAwxInventory.inventoryId.eq(request.getInventoryId()))
                .fetchOne();

        AwxPlayBookEntity awxPlayBookEntity = queryFactory.select(qAwxPlayBook)
                .from(qAwxPlayBook)
                .where(qAwxPlayBook.type.eq(request.getPlaybookType()))
                .fetchOne();

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setAwxCredentialEntity(awxCredentialEntity);
        entity.setAwxInventoryEntity(awxInventory);
        entity.setAwxPlayBookEntity(awxPlayBookEntity);
        entity.setTemplateId(request.getTemplateId());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setJobType(request.getJobType());
        entity.setVerbosity(request.getVerbosity());

        entityManager.persist(entity);

        return AwxTemplateMapper.map(entity);
    }
}
