package com.example.demo.awx.inventory.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class AwxInventoryCreateRequest {

    String awxOrganizationId;
    Long awxId;
    String name;
    String description;
}
