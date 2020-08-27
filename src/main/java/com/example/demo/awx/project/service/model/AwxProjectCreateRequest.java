package com.example.demo.awx.project.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class AwxProjectCreateRequest {

    Long projectId;
    Long organizationId;
    Long credentialId;
    String name;
    String description;
    String scmType;
    String scmUrl;
    String scmBranch;
}
