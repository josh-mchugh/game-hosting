package com.example.demo.awx.host.service;

import com.example.demo.awx.host.entity.AwxHostEntity;
import com.example.demo.awx.host.entity.QAwxHostEntity;
import com.example.demo.awx.host.entity.mapper.AwxHostMapper;
import com.example.demo.awx.host.entity.model.AwxHost;
import com.example.demo.awx.host.service.model.AwxHostCreateRequest;
import com.example.demo.awx.host.service.model.AwxHostDisableRequest;
import com.example.demo.awx.host.service.model.AwxHostEnableRequest;
import com.example.demo.awx.inventory.entity.AwxInventoryEntity;
import com.example.demo.awx.inventory.entity.QAwxInventoryEntity;
import com.example.demo.ovh.instance.entity.InstanceEntity;
import com.example.demo.ovh.instance.entity.QInstanceEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class AwxHostServiceImpl implements AwxHostService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    @EventHandler
    public AwxHost handleCreate(AwxHostCreateRequest request) {

        QAwxInventoryEntity qAwxInventory = QAwxInventoryEntity.awxInventoryEntity;
        QInstanceEntity qInstance = QInstanceEntity.instanceEntity;

        AwxInventoryEntity awxInventoryEntity = queryFactory.select(qAwxInventory)
                .from(qAwxInventory)
                .where(qAwxInventory.id.eq(request.getAwxInventoryId().toString()))
                .fetchOne();

        InstanceEntity instanceEntity = queryFactory.select(qInstance)
                .from(qInstance)
                .where(qInstance.id.eq(request.getInstanceId().toString()))
                .fetchOne();

        AwxHostEntity entity = new AwxHostEntity();
        entity.setAwxInventoryEntity(awxInventoryEntity);
        entity.setInstanceEntity(instanceEntity);
        entity.setAwxId(request.getAwxId());
        entity.setHostname(request.getHostname());
        entity.setDescription(request.getDescription());
        entity.setEnabled(request.getEnabled());

        entityManager.persist(entity);

        return AwxHostMapper.map(entity);
    }

    @Override
    @EventHandler
    public AwxHost handleEnable(AwxHostEnableRequest request) {

        AwxHostEntity entity = findById(request.getId());
        entity.setEnabled(true);

        entityManager.persist(entity);

        return AwxHostMapper.map(entity);
    }

    @Override
    @EventHandler
    public AwxHost handleDisable(AwxHostDisableRequest request) {

        AwxHostEntity entity = findById(request.getId());
        entity.setEnabled(false);

        entityManager.persist(entity);

        return AwxHostMapper.map(entity);
    }

    private AwxHostEntity findById(String id) {

        QAwxHostEntity qAwxHost = QAwxHostEntity.awxHostEntity;

        return queryFactory.select(qAwxHost)
                .from(qAwxHost)
                .where(qAwxHost.id.eq(id))
                .fetchOne();
    }
}
