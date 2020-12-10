package com.example.demo.awx.inventory.feign.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventoryApi {

    private Long id;
    private String name;
    private String description;

    @JsonProperty("organization")
    private Long organizationId;
}
