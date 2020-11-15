package com.example.demo.email.aggregate.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class EmailSentCommand {

    @NotNull
    @TargetAggregateIdentifier
    UUID id;

    @NotNull
    LocalDateTime sentDate;
}
