package com.example.demo.awx.host.aggregate.event;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxHostCreatedEvent {

    UUID id;
    UUID awxInventoryId;
    UUID instanceId;
    Long awxId;
    String hostname;
    String description;
    Boolean enabled;
}
