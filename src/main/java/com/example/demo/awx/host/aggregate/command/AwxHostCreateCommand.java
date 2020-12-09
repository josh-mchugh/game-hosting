package com.example.demo.awx.host.aggregate.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxHostCreateCommand {

    @NotNull
    @TargetAggregateIdentifier
    UUID id;

    @NotBlank
    String awxInventoryId;

    @NotBlank
    String instanceId;

    @NotNull
    Long awxId;

    @NotBlank
    String hostname;

    String description;

    @NotNull
    Boolean enabled;
}
