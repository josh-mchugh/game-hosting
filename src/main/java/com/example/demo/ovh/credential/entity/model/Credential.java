package com.example.demo.ovh.credential.entity.model;

import com.example.demo.ovh.credential.entity.CredentialType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class Credential {

    String id;
    String sshKeyId;
    String name;
    String publicKey;
    CredentialType type;
}
