package com.example.demo.awx.inventory.entity.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class AwxInventory {

    String id;
    Long awxId;
    String name;
    String description;
}
