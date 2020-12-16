package com.example.demo.ovh.credential.aggregate.event;

import com.example.demo.ovh.credential.entity.CredentialType;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class CredentialCreatedEvent {

    UUID id;
    String ovhId;
    String name;
    String publicKey;
    CredentialType type;
}
