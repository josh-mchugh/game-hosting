package com.example.demo.ovh.credential.service.model;

import com.example.demo.ovh.credential.entity.CredentialType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class CredentialCreateRequest {

    String sshKeyId;
    String name;
    String publicKey;
    String privateKey;
    CredentialType type;
}
