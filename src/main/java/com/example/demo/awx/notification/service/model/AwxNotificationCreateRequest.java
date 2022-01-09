package com.example.demo.awx.notification.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class AwxNotificationCreateRequest {

    String awxOrganizationId;
    Long awxId;
    String name;
    String description;
    String type;
    String webhookCallBackUrl;
}
