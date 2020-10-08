package com.example.demo.awx.inventory.aggregate.command;

import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
public class AwxInventoryCreateCommand {

    @NotNull
    @TargetAggregateIdentifier
    private final UUID id;

    @NotNull
    private final Long organizationId;

    @NotNull
    private final Long inventoryId;

    @NotBlank
    private final String name;

    private final String description;
}
