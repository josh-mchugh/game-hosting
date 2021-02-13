package com.example.demo.ovh.instance.entity.service;

import com.example.demo.ovh.credential.entity.CredentialEntity;
import com.example.demo.ovh.credential.entity.QCredentialEntity;
import com.example.demo.ovh.flavor.entity.FlavorEntity;
import com.example.demo.ovh.flavor.entity.QFlavorEntity;
import com.example.demo.ovh.image.entity.ImageEntity;
import com.example.demo.ovh.image.entity.QImageEntity;
import com.example.demo.ovh.instance.aggregate.event.InstanceCreatedEvent;
import com.example.demo.ovh.instance.aggregate.event.InstanceUpdatedEvent;
import com.example.demo.ovh.instance.entity.InstanceEntity;
import com.example.demo.ovh.instance.entity.InstanceGroupEntity;
import com.example.demo.ovh.instance.entity.QInstanceEntity;
import com.example.demo.ovh.instance.entity.QInstanceGroupEntity;
import com.example.demo.ovh.instance.entity.mapper.InstanceMapper;
import com.example.demo.ovh.instance.entity.model.Instance;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.UUID;

@Component
@Transactional
@RequiredArgsConstructor
public class InstanceService implements IInstanceService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    @EventHandler
    public Instance handleCreated(InstanceCreatedEvent event) {

        QImageEntity qImage = QImageEntity.imageEntity;
        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;
        QInstanceGroupEntity qInstanceGroup = QInstanceGroupEntity.instanceGroupEntity;
        QCredentialEntity qCredential = QCredentialEntity.credentialEntity;

        FlavorEntity flavorEntity = queryFactory.selectFrom(qFlavor)
                .where(qFlavor.id.eq(event.getFlavorId().toString()))
                .fetchOne();

        ImageEntity imageEntity = queryFactory.selectFrom(qImage)
                .where(qImage.id.eq(event.getImageId()))
                .fetchOne();

        InstanceGroupEntity instanceGroupEntity = queryFactory.selectFrom(qInstanceGroup)
                .where(qInstanceGroup.id.eq(event.getInstanceGroupId()))
                .fetchOne();

        CredentialEntity credentialEntity = queryFactory.select(qCredential)
                .from(qCredential)
                .where(qCredential.id.eq(event.getCredentialId().toString()))
                .fetchOne();

        InstanceEntity entity = new InstanceEntity();
        entity.setId(event.getId());
        entity.setFlavorEntity(flavorEntity);
        entity.setImageEntity(imageEntity);
        entity.setInstanceGroupEntity(instanceGroupEntity);
        entity.setOvhId(event.getOvhId());
        entity.setCredentialEntity(credentialEntity);
        entity.setName(event.getName());
        entity.setStatus(event.getStatus());
        entity.setInstanceCreatedDate(event.getInstanceCreatedDate());

        entityManager.persist(entity);

        return InstanceMapper.map(entity);
    }

    @Override
    @EventHandler
    public Instance handleUpdated(InstanceUpdatedEvent event) {

        InstanceEntity entity = findById(event.getId());
        entity.setName(event.getName());
        entity.setStatus(event.getStatus());
        entity.setInstanceCreatedDate(event.getInstanceCreatedDate());
        entity.setIp4Address(event.getIp4Address());
        entity.setIp6Address(event.getIp6Address());

        entityManager.persist(entity);

        return InstanceMapper.map(entity);
    }

    private InstanceEntity findById(UUID id) {

        return findById(id.toString());
    }

    private InstanceEntity findById(String id) {

        QInstanceEntity qInstance = QInstanceEntity.instanceEntity;

        return queryFactory.selectFrom(qInstance)
                .where(qInstance.id.eq(id))
                .fetchOne();
    }
}
