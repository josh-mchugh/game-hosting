package com.example.demo.awx.project.aggregate.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxProjectCreateCommand {

    @NotNull
    @TargetAggregateIdentifier
    UUID id;

    @NotNull
    UUID awxOrganizationId;

    @NotNull
    UUID awxCredentialId;

    @NotNull
    Long awxId;

    @NotBlank
    String name;

    String description;

    @NotBlank
    String scmType;

    @NotBlank
    String scmUrl;

    @NotBlank
    String scmBranch;
}
