package com.example.demo.awx.playbook.aggregate.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxPlaybookCreateCommand {

    @NotNull
    @TargetAggregateIdentifier
    UUID id;

    @NotNull
    UUID awxProjectId;

    @NotBlank
    String name;
}
