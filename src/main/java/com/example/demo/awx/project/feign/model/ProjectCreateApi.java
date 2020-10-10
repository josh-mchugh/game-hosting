package com.example.demo.awx.project.feign.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class ProjectCreateApi {

    String name;
    String description;

    @JsonProperty("scm_type")
    String scmType;

    @JsonProperty("scm_url")
    String scmUrl;

    @JsonProperty("scm_branch")
    String scmBranch;

    @JsonProperty("credential")
    Long credentialId;
}
