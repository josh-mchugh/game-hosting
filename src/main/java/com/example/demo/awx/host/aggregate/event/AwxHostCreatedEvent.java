package com.example.demo.awx.host.aggregate.event;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxHostCreatedEvent {

    UUID id;
    String awxInventoryId;
    String instanceId;
    Long awxId;
    String hostname;
    String description;
    Boolean enabled;
}
