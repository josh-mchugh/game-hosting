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
public class AwxTemplateServiceImpl implements AwxTemplateService {

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
                .where(qAwxCredential.id.eq(event.getAwxCredentialId().toString()))
                .fetchOne();

        AwxInventoryEntity awxInventory = queryFactory.select(qAwxInventory)
                .from(qAwxInventory)
                .where(qAwxInventory.id.eq(event.getAwxInventoryId().toString()))
                .fetchOne();

        AwxPlayBookEntity awxPlayBookEntity = queryFactory.select(qAwxPlayBook)
                .from(qAwxPlayBook)
                .where(qAwxPlayBook.id.eq(event.getAwxPlaybookId().toString()))
                .fetchOne();

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setId(event.getId());
        entity.setAwxCredentialEntity(awxCredentialEntity);
        entity.setAwxInventoryEntity(awxInventory);
        entity.setAwxPlayBookEntity(awxPlayBookEntity);
        entity.setAwxId(event.getAwxId());
        entity.setName(event.getName());
        entity.setDescription(event.getDescription());
        entity.setType(event.getType());
        entity.setVerbosity(event.getVerbosity());

        entityManager.persist(entity);

        return AwxTemplateMapper.map(entity);
    }
}
