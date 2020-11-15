package com.example.demo.game.aggregate.command;

import com.example.demo.game.entity.GameType;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class GameCreateCommand {

    @NotNull
    @TargetAggregateIdentifier
    UUID id;

    @NotNull
    GameType type;
}
