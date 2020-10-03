package com.example.demo.awx.playbook.aggregate.command;

import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
public class AwxPlaybookCreateCommand {

    @NotNull
    @TargetAggregateIdentifier
    private final UUID id;

    @NotBlank
    private final String awxProjectId;

    @NotBlank
    private final String name;
}
