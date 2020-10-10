package com.example.demo.awx.organization.feign.model;

import com.example.demo.awx.feign.AbstractBase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganizationApi extends AbstractBase {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("max_hosts")
    private Integer maxHosts;

    @JsonProperty("custom_virtualenv")
    private String customVirtualEnv;
}
