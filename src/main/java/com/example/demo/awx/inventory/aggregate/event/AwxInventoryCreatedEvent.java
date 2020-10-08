package com.example.demo.awx.inventory.aggregate.event;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
public class AwxInventoryCreatedEvent {

    private final UUID id;
    private final Long organizationId;
    private final Long inventoryId;
    private final String name;
    private final String description;
}
