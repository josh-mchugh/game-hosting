package com.example.demo.awx.notification.entity.service;

import com.example.demo.awx.notification.aggregate.event.AwxNotificationCreatedEvent;
import com.example.demo.awx.notification.entity.model.AwxNotification;

public interface IAwxNotificationService {

    AwxNotification handleCreated(AwxNotificationCreatedEvent event);
}
