package com.example.demo.awx.credential.service.model;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxCredentialCreateRequest {

    UUID awxOrganizationId;
    Long awxId;
    String name;
    String description;
    String privateKey;
    String passphrase;
    AwxCredentialType type;
}
