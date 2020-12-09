package com.example.demo.awx.host.feign.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HostApi {

    private Long id;

    @JsonProperty("inventory")
    private Long inventoryId;

    private String name;
    private String description;
    private Boolean enabled;
}
