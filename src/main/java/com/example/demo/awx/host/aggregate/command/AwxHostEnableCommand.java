package com.example.demo.awx.host.aggregate.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class AwxHostEnableCommand {

    @NotNull
    @TargetAggregateIdentifier
    private final UUID id;
}
