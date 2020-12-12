package com.example.demo.awx.organization.aggregate.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxOrganizationCreateCommand {

    @NotNull
    @TargetAggregateIdentifier
    UUID id;

    @NotNull
    Long awxId;

    @NotBlank
    String name;

    String description;
}
