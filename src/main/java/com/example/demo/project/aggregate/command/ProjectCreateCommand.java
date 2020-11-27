package com.example.demo.project.aggregate.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class ProjectCreateCommand {

    @NotNull
    @TargetAggregateIdentifier
    UUID id;

    @NotBlank
    String name;

    @NotBlank
    String userId;

    @NotBlank
    String gameId;
}
