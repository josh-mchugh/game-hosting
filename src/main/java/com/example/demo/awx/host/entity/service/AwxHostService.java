package com.example.demo.awx.host.entity.service;

import com.example.demo.awx.host.aggregate.event.AwxHostCreatedEvent;
import com.example.demo.awx.host.aggregate.event.AwxHostDisabledEvent;
import com.example.demo.awx.host.aggregate.event.AwxHostEnabledEvent;
import com.example.demo.awx.host.entity.AwxHostEntity;
import com.example.demo.awx.host.entity.QAwxHostEntity;
import com.example.demo.awx.host.entity.mapper.AwxHostMapper;
import com.example.demo.awx.host.entity.model.AwxHost;
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
public class AwxHostService implements IAwxHostService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    @EventHandler
    public AwxHost handleCreated(AwxHostCreatedEvent event) {

        QAwxInventoryEntity qAwxInventory = QAwxInventoryEntity.awxInventoryEntity;
        QInstanceEntity qInstance = QInstanceEntity.instanceEntity;

        AwxInventoryEntity awxInventoryEntity = queryFactory.select(qAwxInventory)
                .from(qAwxInventory)
                .where(qAwxInventory.id.eq(event.getAwxInventoryId()))
                .fetchOne();

        InstanceEntity instanceEntity = queryFactory.select(qInstance)
                .from(qInstance)
                .where(qInstance.id.eq(event.getInstanceId()))
                .fetchOne();

        AwxHostEntity entity = new AwxHostEntity();
        entity.setId(event.getId().toString());
        entity.setAwxInventoryEntity(awxInventoryEntity);
        entity.setInstanceEntity(instanceEntity);
        entity.setHostId(event.getHostId());
        entity.setHostname(event.getHostname());
        entity.setDescription(event.getDescription());
        entity.setEnabled(event.getEnabled());

        entityManager.persist(entity);

        return AwxHostMapper.map(entity);
    }

    @Override
    @EventHandler
    public AwxHost handleEnabled(AwxHostEnabledEvent event) {

        AwxHostEntity entity = findById(event.getId().toString());
        entity.setEnabled(true);

        entityManager.persist(entity);

        return AwxHostMapper.map(entity);
    }

    @Override
    @EventHandler
    public AwxHost handleDisabled(AwxHostDisabledEvent event) {

        AwxHostEntity entity = findById(event.getId().toString());
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
