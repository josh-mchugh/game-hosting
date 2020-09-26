package com.example.demo.awx.notification.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class AwxNotificationCreateRequest {

    Long notificationId;
    Long organizationId;
    String name;
    String description;
    String notificationType;
    String webhookCallbackUrl;

}
