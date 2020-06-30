package com.example.demo.web.ansible.model.inventories;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InventoryRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("organization")
    private Integer organizationId;

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("host_filter")
    private String hostFilter;

    @JsonProperty("variables")
    private String variables;

    @JsonProperty("insights_credential")
    private String insightsCredential;
}
