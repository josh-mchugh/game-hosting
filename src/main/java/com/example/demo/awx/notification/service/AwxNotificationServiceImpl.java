package com.example.demo.awx.notification.service;

import com.example.demo.awx.notification.entity.AwxNotificationEntity;
import com.example.demo.awx.notification.entity.mapper.AwxNotificationMapper;
import com.example.demo.awx.notification.entity.model.AwxNotification;
import com.example.demo.awx.notification.service.model.AwxNotificationCreateRequest;
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
public class AwxNotificationServiceImpl implements AwxNotificationService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    public AwxNotification handleCreated(AwxNotificationCreateRequest request) {

        QAwxOrganizationEntity qAwxOrganization = QAwxOrganizationEntity.awxOrganizationEntity;

        //TODO: replace with service / repository call
        AwxOrganizationEntity awxOrganizationEntity = queryFactory.select(qAwxOrganization)
                .from(qAwxOrganization)
                .where(qAwxOrganization.id.eq(request.getAwxOrganizationId().toString()))
                .fetchOne();

        AwxNotificationEntity entity = new AwxNotificationEntity();
        entity.setAwxOrganizationEntity(awxOrganizationEntity);
        entity.setAwxId(request.getAwxId());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setType(request.getType());
        entity.setWebhookCallBackUrl(request.getWebhookCallBackUrl());

        entityManager.persist(entity);

        return AwxNotificationMapper.map(entity);
    }
}
