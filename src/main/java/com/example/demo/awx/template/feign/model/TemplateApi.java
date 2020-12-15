package com.example.demo.awx.template.feign.model;

import com.example.demo.awx.template.entity.TemplateJobType;
import com.example.demo.awx.template.entity.TemplateVerbosity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateApi {

    private Long id;
    private String name;
    private String description;
    private String playbook;
    private String limit;


    @JsonProperty("inventory")
    private Long inventoryId;

    @JsonProperty("project")
    private Long projectId;

    @JsonProperty("job_type")
    private TemplateJobType jobType;

    @JsonProperty("verbosity")
    private TemplateVerbosity verbosity;
}
