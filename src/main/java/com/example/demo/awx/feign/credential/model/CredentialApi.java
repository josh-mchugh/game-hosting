package com.example.demo.awx.feign.credential.model;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import com.example.demo.awx.feign.common.AbstractBase;
import com.example.demo.framework.deserializer.AwxCredentialTypeDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CredentialApi extends AbstractBase {

    private String name;
    private String description;
    private String kind;

    @JsonProperty("organization")
    private Long organizationId;

    @JsonProperty("credential_type")
    @JsonDeserialize(using = AwxCredentialTypeDeserializer.class)
    private AwxCredentialType credentialType;
}
