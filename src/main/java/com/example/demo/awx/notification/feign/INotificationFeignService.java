package com.example.demo.awx.notification.feign;

import com.example.demo.awx.notification.feign.model.NotificationApi;
import com.example.demo.awx.notification.feign.model.NotificationCreateApi;

public interface INotificationFeignService {

    NotificationApi createSuccessNotificationForProject(Long projectId, NotificationCreateApi body);
}
