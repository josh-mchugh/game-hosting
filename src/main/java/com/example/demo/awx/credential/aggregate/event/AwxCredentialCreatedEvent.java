package com.example.demo.awx.credential.aggregate.event;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
public class AwxCredentialCreatedEvent {

    private final UUID id;
    private final Long organizationId;
    private final Long credentialId;
    private final String name;
    private final String description;
    private final String privateKey;
    private final String passphrase;
    private final AwxCredentialType type;
}
