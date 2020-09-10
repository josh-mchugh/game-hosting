package com.example.demo.awx.host.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class AwxHostCreateRequest {

    Long inventoryId;
    String instanceId;
    Long hostId;
    String hostname;
    String description;
    Boolean enabled;
}
