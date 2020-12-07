package com.example.demo.awx.credential.feign.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@Builder(builderClassName = "Builder")
public class AwxCredentialCreateApi {

    @NotBlank
    String name;
    @lombok.Builder.Default
    String description = "";
    Input inputs;

    @JsonProperty("credential_type")
    Integer credentialType;

    @Value
    @lombok.Builder(builderClassName = "Builder")
    public static class Input {

        @JsonProperty("ssh_key_data")
        String privateKey;

        @JsonProperty("ssh_key_unlock")
        String passphrase;
    }
}
