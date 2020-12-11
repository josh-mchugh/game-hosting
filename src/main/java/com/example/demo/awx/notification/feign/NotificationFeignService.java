package com.example.demo.awx.notification.feign;

import com.example.demo.awx.notification.feign.model.NotificationApi;
import com.example.demo.awx.notification.feign.model.NotificationCreateApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationFeignService implements INotificationFeignService {

    private final INotificationClient notificationClient;

    @Override
    public NotificationApi createSuccessNotificationForProject(Long projectId, NotificationCreateApi body) {

        return notificationClient.createSuccessNotificationForProject(projectId, body);
    }
}
