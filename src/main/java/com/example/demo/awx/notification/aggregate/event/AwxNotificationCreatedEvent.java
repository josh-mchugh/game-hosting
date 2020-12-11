package com.example.demo.awx.notification.aggregate.event;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxNotificationCreatedEvent {

    UUID id;
    String awxOrganizationId;
    Long awxId;
    String name;
    String description;
    String type;
    String webhookCallBackUrl;
}
