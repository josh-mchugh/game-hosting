package com.example.demo.awx.feign.notification.model;

import com.example.demo.awx.feign.common.AbstractBase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationApi extends AbstractBase {

    private String name;
    private String description;

    @JsonProperty("notification_configuration")
    private NotificationConfiguration notificationConfiguration;

    @JsonProperty("notification_type")
    private String notificationType;

    @JsonProperty("organization")
    private Long organizationId;
}
