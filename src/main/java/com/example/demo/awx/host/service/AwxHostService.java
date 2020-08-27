package com.example.demo.awx.host.service;

import com.example.demo.awx.host.entity.AwxHostEntity;
import com.example.demo.awx.host.entity.QAwxHostEntity;
import com.example.demo.awx.host.mapper.AwxHostMapper;
import com.example.demo.awx.host.model.AwxHost;
import com.example.demo.awx.host.service.model.AwxHostCreateRequest;
import com.example.demo.awx.inventory.entity.AwxInventoryEntity;
import com.example.demo.awx.inventory.entity.QAwxInventoryEntity;
import com.example.demo.ovh.instance.entity.InstanceEntity;
import com.example.demo.ovh.instance.entity.QInstanceEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class AwxHostService implements IAwxHostService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    public boolean existsAny() {

        QAwxHostEntity qAwxHost = QAwxHostEntity.awxHostEntity;

        long count = queryFactory.select(qAwxHost)
                .from(qAwxHost)
                .fetchCount();

        return count >= 1;
    }

    @Override
    public AwxHost handleCreateRequest(AwxHostCreateRequest request) {

        QAwxInventoryEntity qAwxInventory = QAwxInventoryEntity.awxInventoryEntity;
        QInstanceEntity qInstance = QInstanceEntity.instanceEntity;

        AwxInventoryEntity awxInventoryEntity = queryFactory.select(qAwxInventory)
                .from(qAwxInventory)
                .where(qAwxInventory.inventoryId.eq(request.getInventoryId()))
                .fetchOne();

        InstanceEntity instanceEntity = queryFactory.select(qInstance)
                .from(qInstance)
                .where(qInstance.id.eq(request.getInstanceId()))
                .fetchOne();

        AwxHostEntity entity = new AwxHostEntity();
        entity.setAwxInventoryEntity(awxInventoryEntity);
        entity.setInstanceEntity(instanceEntity);
        entity.setHostname(request.getHostname());
        entity.setDescription(request.getDescription());

        entityManager.persist(entity);

        return AwxHostMapper.map(entity);
    }
}
