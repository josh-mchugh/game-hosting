package com.example.demo.awx.project.entity.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class AwxProject {

    String id;
    Long awxId;
    String name;
    String description;
    String scmType;
    String scmUrl;
    String scmBranch;
}
