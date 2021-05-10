package com.example.demo.project.aggregate.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class ProjectFlavorAddCommand {

    @NotNull
    @TargetAggregateIdentifier
    UUID id;

    @NotNull
    UUID ovhFlavorId;
}
