package com.example.demo.ovh.credential.entity.model;

import com.example.demo.ovh.credential.entity.CredentialType;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class Credential {

    UUID id;
    String sshKeyId;
    String name;
    String publicKey;
    CredentialType type;
}
