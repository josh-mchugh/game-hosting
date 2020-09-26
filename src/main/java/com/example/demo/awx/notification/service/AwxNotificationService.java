package com.example.demo.awx.notification.service;

import com.example.demo.awx.notification.entity.AwxNotificationEntity;
import com.example.demo.awx.notification.mapper.AwxNotificationMapper;
import com.example.demo.awx.notification.model.AwxNotification;
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
public class AwxNotificationService implements IAwxNotificationService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    public AwxNotification handleCreateNotification(AwxNotificationCreateRequest request) {

        QAwxOrganizationEntity qAwxOrganization = QAwxOrganizationEntity.awxOrganizationEntity;

        AwxOrganizationEntity awxOrganizationEntity = queryFactory.select(qAwxOrganization)
                .from(qAwxOrganization)
                .where(qAwxOrganization.organizationId.eq(request.getOrganizationId()))
                .fetchOne();

        AwxNotificationEntity entity = new AwxNotificationEntity();
        entity.setAwxOrganizationEntity(awxOrganizationEntity);
        entity.setNotificationId(request.getNotificationId());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setNotificationType(request.getNotificationType());
        entity.setWebhookCallBackUrl(request.getWebhookCallbackUrl());

        entityManager.persist(entity);

        return AwxNotificationMapper.map(entity);
    }
}
