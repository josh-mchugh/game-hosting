package com.example.demo.awx.credential.entity.model;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class AwxCredential {

    String id;
    Long credentialId;
    String name;
    String description;
    String privateKey;
    String passphrase;
    AwxCredentialType type;
}
