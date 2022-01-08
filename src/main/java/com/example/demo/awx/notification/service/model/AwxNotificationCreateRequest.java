package com.example.demo.awx.notification.service.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxNotificationCreateRequest {

    UUID awxOrganizationId;
    Long awxId;
    String name;
    String description;
    String type;
    String webhookCallBackUrl;
}
