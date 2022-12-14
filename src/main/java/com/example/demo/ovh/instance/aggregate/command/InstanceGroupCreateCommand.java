package com.example.demo.ovh.instance.aggregate.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class InstanceGroupCreateCommand {

    @NotNull
    @TargetAggregateIdentifier
    UUID id;

    @NotNull
    UUID projectId;

    @NotBlank
    String ovhId;

    String name;
    String type;
}
