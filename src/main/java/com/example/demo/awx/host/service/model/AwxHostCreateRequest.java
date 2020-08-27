package com.example.demo.awx.host.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class AwxHostCreateRequest {

    Long inventoryId;
    String instanceId;
    String hostname;
    String description;
}
