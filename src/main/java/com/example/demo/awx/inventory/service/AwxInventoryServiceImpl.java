package com.example.demo.awx.inventory.service;

import com.example.demo.awx.inventory.entity.AwxInventoryEntity;
import com.example.demo.awx.inventory.entity.mapper.AwxInventoryMapper;
import com.example.demo.awx.inventory.entity.model.AwxInventory;
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
public class AwxInventoryServiceImpl implements AwxInventoryService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    public AwxInventory handleCreate(AwxInventoryCreateRequest request) {

        QAwxOrganizationEntity qAwxOrganization = QAwxOrganizationEntity.awxOrganizationEntity;

        //TODO: replace with service / repository call
        AwxOrganizationEntity organizationEntity = queryFactory.select(qAwxOrganization)
                .from(qAwxOrganization)
                .where(qAwxOrganization.id.eq(request.getAwxOrganizationId()))
                .fetchOne();

        AwxInventoryEntity entity = new AwxInventoryEntity();
        entity.setAwxOrganizationEntity(organizationEntity);
        entity.setAwxId(request.getAwxId());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());

        entityManager.persist(entity);

        return AwxInventoryMapper.map(entity);
    }
}
