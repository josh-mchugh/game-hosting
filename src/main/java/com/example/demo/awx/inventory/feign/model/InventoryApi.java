package com.example.demo.awx.inventory.feign.model;

import com.example.demo.awx.feign.AbstractBase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventoryApi extends AbstractBase {

    private String name;
    private String description;

    @JsonProperty("organization")
    private Long organizationId;
}
