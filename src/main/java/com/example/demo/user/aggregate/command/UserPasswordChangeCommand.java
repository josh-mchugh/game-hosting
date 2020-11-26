package com.example.demo.user.aggregate.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class UserPasswordChangeCommand {

    @NotNull
    @TargetAggregateIdentifier
    UUID id;

    @NotBlank
    String password;
}
