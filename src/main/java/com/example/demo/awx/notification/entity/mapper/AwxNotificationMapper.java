package com.example.demo.awx.notification.entity.mapper;

import com.example.demo.awx.notification.entity.AwxNotificationEntity;
import com.example.demo.awx.notification.entity.model.AwxNotification;

public class AwxNotificationMapper {

    public static AwxNotification map(AwxNotificationEntity entity) {

        if (entity == null) {

            return null;
        }

        return AwxNotification.builder()
                .id(entity.getId())
                .awxId(entity.getAwxId())
                .name(entity.getName())
                .description(entity.getDescription())
                .type(entity.getType())
                .webhookCallbackUrl(entity.getWebhookCallBackUrl())
                .build();
    }
}
