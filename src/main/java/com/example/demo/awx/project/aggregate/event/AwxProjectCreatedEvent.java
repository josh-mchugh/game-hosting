package com.example.demo.awx.project.aggregate.event;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxProjectCreatedEvent {

    UUID id;
    String awxOrganizationId;
    String awxCredentialId;
    Long awxId;
    String name;
    String description;
    String scmType;
    String scmUrl;
    String scmBranch;
}
