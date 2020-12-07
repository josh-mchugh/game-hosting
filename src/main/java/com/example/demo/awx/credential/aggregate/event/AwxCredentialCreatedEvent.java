package com.example.demo.awx.credential.aggregate.event;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxCredentialCreatedEvent {

    UUID id;
    String awxOrganizationId;
    Long awxId;
    String name;
    String description;
    String privateKey;
    String passphrase;
    AwxCredentialType type;
}
