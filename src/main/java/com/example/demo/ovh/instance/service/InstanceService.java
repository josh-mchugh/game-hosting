package com.example.demo.ovh.instance.service;

import com.example.demo.ovh.credential.entity.CredentialEntity;
import com.example.demo.ovh.credential.entity.QCredentialEntity;
import com.example.demo.ovh.flavor.entity.FlavorEntity;
import com.example.demo.ovh.flavor.entity.QFlavorEntity;
import com.example.demo.ovh.image.entity.ImageEntity;
import com.example.demo.ovh.image.entity.QImageEntity;
import com.example.demo.ovh.instance.entity.InstanceEntity;
import com.example.demo.ovh.instance.entity.InstanceGroupEntity;
import com.example.demo.ovh.instance.entity.QInstanceEntity;
import com.example.demo.ovh.instance.entity.QInstanceGroupEntity;
import com.example.demo.ovh.instance.mapper.InstanceMapper;
import com.example.demo.ovh.instance.model.Instance;
import com.example.demo.ovh.instance.service.model.InstanceCreateRequest;
import com.example.demo.ovh.instance.service.model.InstanceUpdateRequest;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class InstanceService implements IInstanceService{

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    public Instance handleInstanceCreate(InstanceCreateRequest request) {

        QImageEntity qImage = QImageEntity.imageEntity;
        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;
        QInstanceGroupEntity qInstanceGroup = QInstanceGroupEntity.instanceGroupEntity;
        QCredentialEntity qCredential = QCredentialEntity.credentialEntity;

        FlavorEntity flavorEntity = queryFactory.selectFrom(qFlavor)
                .where(qFlavor.flavorId.eq(request.getFlavorId()))
                .fetchOne();

        ImageEntity imageEntity = queryFactory.selectFrom(qImage)
                .where(qImage.imageId.eq(request.getImageId()))
                .fetchOne();

        InstanceGroupEntity instanceGroupEntity = queryFactory.selectFrom(qInstanceGroup)
                .where(qInstanceGroup.groupId.eq(request.getGroupId()))
                .fetchOne();

        CredentialEntity credentialEntity = queryFactory.select(qCredential)
                .from(qCredential)
                .where(qCredential.sshKeyId.eq(request.getSshKeyId()))
                .fetchOne();

        InstanceEntity entity = new InstanceEntity();
        entity.setFlavorEntity(flavorEntity);
        entity.setImageEntity(imageEntity);
        entity.setInstanceGroupEntity(instanceGroupEntity);
        entity.setInstanceId(request.getInstanceId());
        entity.setCredentialEntity(credentialEntity);
        entity.setName(request.getName());
        entity.setStatus(request.getStatus());
        entity.setInstanceCreatedDate(request.getInstanceCreatedDate());
        entity.setIp4Address(request.getIp4Address());
        entity.setIp6Address(request.getIp6Address());

        entityManager.persist(entity);

        return InstanceMapper.map(entity);
    }

    @Override
    public Instance handleInstanceUpdate(InstanceUpdateRequest request) {

        InstanceEntity entity = findById(request.getId());
        entity.setName(request.getName());
        entity.setStatus(request.getStatus());
        entity.setInstanceCreatedDate(request.getInstanceCreatedDate());
        entity.setIp4Address(request.getIp4Address());
        entity.setIp6Address(request.getIp6Address());

        entityManager.persist(entity);

        return InstanceMapper.map(entity);
    }

    private InstanceEntity findById(String id) {

        QInstanceEntity qInstance = QInstanceEntity.instanceEntity;

        return queryFactory.selectFrom(qInstance)
                .where(qInstance.id.eq(id))
                .fetchOne();
    }
}
