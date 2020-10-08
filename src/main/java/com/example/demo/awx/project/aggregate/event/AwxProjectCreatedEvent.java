package com.example.demo.awx.project.aggregate.event;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
public class AwxProjectCreatedEvent {

    private final UUID id;
    private final Long organizationId;
    private final String awxCredentialId;
    private final Long projectId;
    private final String name;
    private final String description;
    private final String scmType;
    private final String scmUrl;
    private final String scmBranch;
}
