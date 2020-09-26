package com.example.demo.awx.notification.mapper;

import com.example.demo.awx.notification.entity.AwxNotificationEntity;
import com.example.demo.awx.notification.model.AwxNotification;

public class AwxNotificationMapper {

    public static AwxNotification map(AwxNotificationEntity entity) {

        if (entity == null) {

            return null;
        }

        return AwxNotification.builder()
                .id(entity.getId())
                .notificationId(entity.getNotificationId())
                .name(entity.getName())
                .description(entity.getDescription())
                .notificationType(entity.getNotificationType())
                .webhookCallbackUrl(entity.getWebhookCallBackUrl())
                .build();
    }
}
