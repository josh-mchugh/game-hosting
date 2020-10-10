package com.example.demo.awx.template.feign.model;

import com.example.demo.awx.template.entity.TemplateJobType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class TemplateCreateApi {

    @JsonProperty("inventory")
    Long inventoryId;

    @JsonProperty("project")
    Long projectId;

    String name;
    String description;
    TemplateJobType jobType;
    String playbook;
    String limit;
    Integer verbosity;
}
