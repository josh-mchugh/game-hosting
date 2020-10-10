package com.example.demo.awx.template.feign.model;

import com.example.demo.awx.feign.AbstractBase;
import com.example.demo.awx.template.entity.TemplateJobType;
import com.example.demo.awx.template.entity.TemplateVerbosity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateApi extends AbstractBase {

    String name;
    String description;
    String playbook;
    String limit;


    @JsonProperty("inventory")
    Long inventoryId;

    @JsonProperty("project")
    Long projectId;

    @JsonProperty("job_type")
    TemplateJobType jobType;

    @JsonProperty("verbosity")
    TemplateVerbosity verbosity;
}
