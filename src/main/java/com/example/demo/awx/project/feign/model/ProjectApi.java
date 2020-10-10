package com.example.demo.awx.project.feign.model;

import com.example.demo.awx.feign.AbstractBase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectApi extends AbstractBase {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
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
