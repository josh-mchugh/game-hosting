package com.example.demo.awx.notification.service;

import com.example.demo.awx.notification.entity.model.AwxNotification;
import com.example.demo.awx.notification.service.model.AwxNotificationCreateRequest;

public interface AwxNotificationService {

    AwxNotification handleCreated(AwxNotificationCreateRequest request);
}
