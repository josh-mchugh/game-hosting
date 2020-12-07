package com.example.demo.awx.credential.aggregate.command;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxCredentialCreateCommand {

    @NotNull
    @TargetAggregateIdentifier
    UUID id;

    @NotBlank
    String awxOrganizationId;

    @NotNull
    Long awxId;

    @NotBlank
    String name;

    String description;

    @NotBlank
    String privateKey;

    String passphrase;

    @NotNull
    AwxCredentialType type;
}
