package com.example.demo.awx.template.entity.service;

import com.example.demo.awx.credential.entity.AwxCredentialEntity;
import com.example.demo.awx.credential.entity.QAwxCredentialEntity;
import com.example.demo.awx.inventory.entity.AwxInventoryEntity;
import com.example.demo.awx.inventory.entity.QAwxInventoryEntity;
import com.example.demo.awx.playbook.entity.AwxPlayBookEntity;
import com.example.demo.awx.playbook.entity.QAwxPlayBookEntity;
import com.example.demo.awx.template.aggregate.event.AwxTemplateCreatedEvent;
import com.example.demo.awx.template.entity.AwxTemplateEntity;
import com.example.demo.awx.template.entity.mapper.AwxTemplateMapper;
import com.example.demo.awx.template.entity.model.AwxTemplate;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
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
    @EventHandler
    public AwxTemplate handleAwxTemplateCreated(AwxTemplateCreatedEvent event) {

        QAwxCredentialEntity qAwxCredential = QAwxCredentialEntity.awxCredentialEntity;
        QAwxInventoryEntity qAwxInventory = QAwxInventoryEntity.awxInventoryEntity;
        QAwxPlayBookEntity qAwxPlayBook = QAwxPlayBookEntity.awxPlayBookEntity;

        AwxCredentialEntity awxCredentialEntity = queryFactory.select(qAwxCredential)
                .from(qAwxCredential)
                .where(qAwxCredential.id.eq(event.getAwxCredentialId()))
                .fetchOne();

        AwxInventoryEntity awxInventory = queryFactory.select(qAwxInventory)
                .from(qAwxInventory)
                .where(qAwxInventory.id.eq(event.getAwxInventoryId()))
                .fetchOne();

        AwxPlayBookEntity awxPlayBookEntity = queryFactory.select(qAwxPlayBook)
                .from(qAwxPlayBook)
                .where(qAwxPlayBook.id.eq(event.getAwxPlaybookId()))
                .fetchOne();

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setId(event.getId().toString());
        entity.setAwxCredentialEntity(awxCredentialEntity);
        entity.setAwxInventoryEntity(awxInventory);
        entity.setAwxPlayBookEntity(awxPlayBookEntity);
        entity.setTemplateId(event.getTemplateId());
        entity.setName(event.getName());
        entity.setDescription(event.getDescription());
        entity.setJobType(event.getJobType());
        entity.setVerbosity(event.getVerbosity());

        entityManager.persist(entity);

        return AwxTemplateMapper.map(entity);
    }
}
