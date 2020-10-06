package com.example.demo.awx.notification.entity.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class AwxNotification {

    String id;
    Long notificationId;
    String name;
    String description;
    String notificationType;
    String webhookCallbackUrl;
}
