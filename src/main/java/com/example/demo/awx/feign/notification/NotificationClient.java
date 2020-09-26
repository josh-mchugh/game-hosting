package com.example.demo.awx.feign.notification;

import com.example.demo.awx.feign.notification.model.NotificationApi;
import com.example.demo.awx.feign.notification.model.NotificationCreateApi;
import com.example.demo.framework.feign.FeignAwxConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificationClient", url = "${awx.base-url}", configuration = FeignAwxConfig.class)
public interface NotificationClient {

    @PostMapping("/api/v2/projects/{projectId}/notification_templates_success/")
    NotificationApi createSuccessNotificationForProject(@PathVariable("projectId") Long projectId, @RequestBody NotificationCreateApi body);
}
