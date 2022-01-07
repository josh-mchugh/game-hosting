package com.example.demo.awx.inventory.service.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxInventoryCreateRequest {

    UUID awxOrganizationId;
    Long awxId;
    String name;
    String description;
}
