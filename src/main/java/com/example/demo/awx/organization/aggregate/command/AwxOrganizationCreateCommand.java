package com.example.demo.awx.organization.aggregate.command;

import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
public class AwxOrganizationCreateCommand {

    @NotNull
    @TargetAggregateIdentifier
    private final UUID id;

    @NotNull
    private final Long organizationId;

    @NotBlank
    private final String name;

    private final String description;
}