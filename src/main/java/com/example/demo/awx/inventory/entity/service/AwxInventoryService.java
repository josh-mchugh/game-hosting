package com.example.demo.awx.inventory.entity.service;

import com.example.demo.awx.inventory.aggregate.event.AwxInventoryCreatedEvent;
import com.example.demo.awx.inventory.entity.AwxInventoryEntity;
import com.example.demo.awx.inventory.entity.mapper.AwxInventoryMapper;
import com.example.demo.awx.inventory.entity.model.AwxInventory;
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
public class AwxInventoryService implements IAwxInventoryService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    @EventHandler
    public AwxInventory handleCreated(AwxInventoryCreatedEvent event) {

        QAwxOrganizationEntity qAwxOrganization = QAwxOrganizationEntity.awxOrganizationEntity;

        AwxOrganizationEntity organizationEntity = queryFactory.select(qAwxOrganization)
                .from(qAwxOrganization)
                .where(qAwxOrganization.organizationId.eq(event.getOrganizationId()))
                .fetchOne();

        AwxInventoryEntity entity = new AwxInventoryEntity();
        entity.setId(event.getId());
        entity.setAwxOrganizationEntity(organizationEntity);
        entity.setInventoryId(event.getInventoryId());
        entity.setName(event.getName());
        entity.setDescription(event.getDescription());

        entityManager.persist(entity);

        return AwxInventoryMapper.map(entity);
    }
}
