package com.example.demo.ovh.credential.aggregate.command;

import com.example.demo.ovh.credential.entity.CredentialType;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class CredentialCreateCommand {

    @NotNull
    @TargetAggregateIdentifier
    UUID id;

    @NotBlank
    String ovhId;

    String name;
    String publicKey;
    CredentialType type;
}
