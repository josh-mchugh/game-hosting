package com.example.demo.awx.feign.host.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class HostCreateApi {

    @JsonProperty("inventory")
    Long inventoryId;

    String name;
    String description;
    Boolean enabled;
}
