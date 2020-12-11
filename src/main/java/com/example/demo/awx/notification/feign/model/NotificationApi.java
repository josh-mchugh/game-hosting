package com.example.demo.awx.notification.feign.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationApi  {

    private Long id;
    private String name;
    private String description;

    @JsonProperty("notification_configuration")
    private NotificationConfiguration notificationConfiguration;

    @JsonProperty("notification_type")
    private String type;

    @JsonProperty("organization")
    private Long organizationId;
}
