package com.example.demo.awx.notification.entity.service;

import com.example.demo.awx.notification.aggregate.event.AwxNotificationCreatedEvent;
import com.example.demo.awx.notification.entity.AwxNotificationEntity;
import com.example.demo.awx.notification.entity.mapper.AwxNotificationMapper;
import com.example.demo.awx.notification.entity.model.AwxNotification;
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
public class AwxNotificationServiceImpl implements AwxNotificationService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    @EventHandler
    public AwxNotification handleCreated(AwxNotificationCreatedEvent event) {

        QAwxOrganizationEntity qAwxOrganization = QAwxOrganizationEntity.awxOrganizationEntity;

        AwxOrganizationEntity awxOrganizationEntity = queryFactory.select(qAwxOrganization)
                .from(qAwxOrganization)
                .where(qAwxOrganization.id.eq(event.getAwxOrganizationId().toString()))
                .fetchOne();

        AwxNotificationEntity entity = new AwxNotificationEntity();
        entity.setId(event.getId());
        entity.setAwxOrganizationEntity(awxOrganizationEntity);
        entity.setAwxId(event.getAwxId());
        entity.setName(event.getName());
        entity.setDescription(event.getDescription());
        entity.setType(event.getType());
        entity.setWebhookCallBackUrl(event.getWebhookCallBackUrl());

        entityManager.persist(entity);

        return AwxNotificationMapper.map(entity);
    }
}
