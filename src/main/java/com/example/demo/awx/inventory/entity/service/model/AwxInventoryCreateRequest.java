package com.example.demo.awx.inventory.entity.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class AwxInventoryCreateRequest {

    Long organizationId;
    Long inventoryId;
    String name;
    String description;
}
