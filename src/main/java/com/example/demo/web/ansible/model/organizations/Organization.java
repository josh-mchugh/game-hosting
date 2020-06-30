package com.example.demo.web.ansible.model.organizations;

import com.example.demo.web.ansible.model.base.AbstractBase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Organization extends AbstractBase {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("max_hosts")
    private Integer maxHosts;

    @JsonProperty("custom_virtualenv")
    private String customVirtualEnv;
}
