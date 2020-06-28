package com.example.demo.controller.ansible.model.organizations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrganizationRequest {

    private String name;
    private String description;

    @JsonProperty("max_hosts")
    private Integer maxHosts;

    @JsonProperty("custom_virtualenv")
    private String customVirtualEnv;
}
