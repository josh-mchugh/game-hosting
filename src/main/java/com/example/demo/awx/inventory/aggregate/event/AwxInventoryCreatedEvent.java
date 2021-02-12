package com.example.demo.awx.inventory.aggregate.event;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxInventoryCreatedEvent {

    UUID id;
    UUID awxOrganizationId;
    Long awxId;
    String name;
    String description;
}
