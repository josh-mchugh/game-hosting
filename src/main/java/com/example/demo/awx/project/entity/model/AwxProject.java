package com.example.demo.awx.project.entity.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxProject {

    UUID id;
    Long awxId;
    String name;
    String description;
    String scmType;
    String scmUrl;
    String scmBranch;
}
