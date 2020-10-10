package com.example.demo.awx.notification.feign.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class NotificationCreateApi {

    String name;
    String description;

    @JsonProperty("organization")
    Long organizationId;

    @JsonProperty("notification_type")
    String notificationType;

    @JsonProperty("notification_configuration")
    NotificationConfiguration notificationConfiguration;

    String messages;
}
