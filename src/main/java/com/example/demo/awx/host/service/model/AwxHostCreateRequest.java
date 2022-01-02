package com.example.demo.awx.host.service.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxHostCreateRequest {

    UUID awxInventoryId;
    UUID instanceId;
    Long awxId;
    String hostname;
    String description;
    Boolean enabled;
}
