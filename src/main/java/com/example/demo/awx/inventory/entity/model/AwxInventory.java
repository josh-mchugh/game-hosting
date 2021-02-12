package com.example.demo.awx.inventory.entity.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxInventory {

    UUID id;
    Long awxId;
    String name;
    String description;
}
