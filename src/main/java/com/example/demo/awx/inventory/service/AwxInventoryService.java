package com.example.demo.awx.inventory.service;

import com.example.demo.awx.inventory.entity.AwxInventoryEntity;
import com.example.demo.awx.inventory.entity.QAwxInventoryEntity;
import com.example.demo.awx.inventory.mapper.AwxInventoryMapper;
import com.example.demo.awx.inventory.model.AwxInventory;
import com.example.demo.awx.inventory.service.model.AwxInventoryCreateRequest;
import com.example.demo.awx.organization.entity.AwxOrganizationEntity;
import com.example.demo.awx.organization.entity.QAwxOrganizationEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
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
    public boolean existsAny() {

        QAwxInventoryEntity qAwxInventory = QAwxInventoryEntity.awxInventoryEntity;

        long count = queryFactory.select(qAwxInventory)
                .from(qAwxInventory)
                .fetchCount();

        return count >= 1;
    }

    @Override
    public AwxInventory findByName(String name) {

        QAwxInventoryEntity qAwxInventory = QAwxInventoryEntity.awxInventoryEntity;

        AwxInventoryEntity entity = queryFactory.select(qAwxInventory)
                .from(qAwxInventory)
                .where(qAwxInventory.name.eq(name))
                .fetchOne();

        return AwxInventoryMapper.map(entity);
    }

    @Override
    public AwxInventory handleCreateRequest(AwxInventoryCreateRequest request) {

        QAwxOrganizationEntity qAwxOrganization = QAwxOrganizationEntity.awxOrganizationEntity;

        AwxOrganizationEntity organizationEntity = queryFactory.select(qAwxOrganization)
                .from(qAwxOrganization)
                .where(qAwxOrganization.organizationId.eq(request.getOrganizationId()))
                .fetchOne();

        AwxInventoryEntity entity = new AwxInventoryEntity();
        entity.setAwxOrganizationEntity(organizationEntity);
        entity.setInventoryId(request.getInventoryId());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());

        entityManager.persist(entity);

        return AwxInventoryMapper.map(entity);
    }
}
