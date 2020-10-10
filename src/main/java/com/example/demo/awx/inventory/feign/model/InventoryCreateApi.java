package com.example.demo.awx.inventory.feign.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class InventoryCreateApi {

    String name;
    String description;

    @JsonProperty("organization")
    Long organizationId;
}
