package com.example.demo.awx.host.feign.model;

import com.example.demo.awx.feign.AbstractBase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HostApi extends AbstractBase {

    @JsonProperty("inventory")
    Long inventoryId;

    String name;
    String description;
    Boolean enabled;
}
