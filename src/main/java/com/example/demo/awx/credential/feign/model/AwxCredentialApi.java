package com.example.demo.awx.credential.feign.model;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import com.example.demo.framework.deserializer.AwxCredentialTypeDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AwxCredentialApi {

    private Long id;
    private String name;
    private String description;

    @JsonProperty("organization")
    private Long organizationId;

    @JsonProperty("credential_type")
    @JsonDeserialize(using = AwxCredentialTypeDeserializer.class)
    private AwxCredentialType credentialType;
}
