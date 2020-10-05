package com.example.demo.awx.host.aggregate.command;

import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
public class AwxHostCreateCommand {

    @NotNull
    @TargetAggregateIdentifier
    private final UUID id;

    @NotBlank
    private final String awxInventoryId;

    @NotBlank
    private final String instanceId;

    @NotNull
    private final Long hostId;

    @NotBlank
    private final String hostname;

    private final String description;

    @NotNull
    private final Boolean enabled;
}
