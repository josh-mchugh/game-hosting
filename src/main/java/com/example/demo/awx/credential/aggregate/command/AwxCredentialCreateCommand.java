package com.example.demo.awx.credential.aggregate.command;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
public class AwxCredentialCreateCommand {

    @NotNull
    @TargetAggregateIdentifier
    private final UUID id;

    @NotNull
    private final Long organizationId;

    @NotNull
    private final Long credentialId;

    @NotBlank
    private final String name;

    private final String description;

    @NotBlank
    private final String privateKey;

    private final String passphrase;

    @NotNull
    private final AwxCredentialType type;
}
