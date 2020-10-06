package com.example.demo.awx.notification.aggregate.event;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
public class AwxNotificationCreatedEvent {

    private final UUID id;
    private final Long organizationId;
    private final Long notificationId;
    private final String name;
    private final String description;
    private final String notificationType;
    private final String webhookCallBackUrl;
}
