package com.example.demo.awx.notification.entity.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxNotification {

    UUID id;
    Long awxId;
    String name;
    String description;
    String type;
    String webhookCallbackUrl;
}
