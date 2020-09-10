package com.example.demo.awx.feign.host.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HostPatchApi {

    @JsonProperty("inventory")
    Long inventoryId;

    String name;
    String description;
    Boolean enabled;
}
