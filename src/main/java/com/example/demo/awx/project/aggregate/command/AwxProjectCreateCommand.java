package com.example.demo.awx.project.aggregate.command;

import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
public class AwxProjectCreateCommand {

    @NotNull
    @TargetAggregateIdentifier
    private final UUID id;

    @NotNull
    private final Long organizationId;

    @NotBlank
    private final String awxCredentialId;

    @NotNull
    private final Long projectId;

    @NotBlank
    private final String name;

    private final String description;

    @NotBlank
    private final String scmType;

    @NotBlank
    private final String scmUrl;

    @NotBlank
    private final String scmBranch;
}
