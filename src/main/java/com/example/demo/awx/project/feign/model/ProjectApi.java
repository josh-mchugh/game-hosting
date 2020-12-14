package com.example.demo.awx.project.feign.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectApi {

    private Long id;
    private String name;
    private String description;

    @JsonProperty("scm_type")
    private String scmType;

    @JsonProperty("scm_url")
    private String scmUrl;

    @JsonProperty("scm_branch")
    private String scmBranch;

    @JsonProperty("credential")
    private Long credentialId;

    @JsonProperty("organization")
    private Long organizationId;
}
