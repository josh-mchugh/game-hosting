package com.example.demo.game.aggregate.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class GameServerCreateCommand {

    @NotNull
    @TargetAggregateIdentifier
    UUID id;

    @NotNull
    UUID gameId;

    @NotNull
    UUID regionId;

    @NotNull
    UUID flavorId;

    @NotNull
    UUID imageId;

    @NotBlank
    String name;

    String description;
}
