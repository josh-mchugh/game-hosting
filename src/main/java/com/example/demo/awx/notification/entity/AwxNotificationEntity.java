package com.example.demo.awx.notification.entity;

import com.example.demo.awx.organization.entity.AwxOrganizationEntity;
import com.example.demo.framework.database.AbstractAggregateEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "awx_notification")
public class AwxNotificationEntity extends AbstractAggregateEntity {

    @Column(name = "notification_id", unique = true, nullable = false)
    private Long notificationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awx_organization_id", nullable = false)
    private AwxOrganizationEntity awxOrganizationEntity;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "notification_type")
    private String notificationType;

    @Column(name = "webhook_callback_url")
    private String webhookCallBackUrl;
}
