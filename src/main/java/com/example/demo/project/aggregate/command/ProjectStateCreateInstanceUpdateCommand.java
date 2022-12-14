package com.example.demo.project.aggregate.command;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@AllArgsConstructor
public class ProjectStateCreateInstanceUpdateCommand {

    @NotNull
    @TargetAggregateIdentifier
    UUID id;
}
