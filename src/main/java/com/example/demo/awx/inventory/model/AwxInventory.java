package com.example.demo.awx.inventory.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class AwxInventory {

    String id;
    Long inventoryId;
    String name;
    String description;
}
