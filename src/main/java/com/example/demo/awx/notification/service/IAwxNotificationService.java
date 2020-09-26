package com.example.demo.awx.notification.service;

import com.example.demo.awx.notification.model.AwxNotification;
import com.example.demo.awx.notification.service.model.AwxNotificationCreateRequest;

public interface IAwxNotificationService {

    AwxNotification handleCreateNotification(AwxNotificationCreateRequest request);
}
