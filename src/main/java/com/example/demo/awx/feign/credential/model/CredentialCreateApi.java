package com.example.demo.awx.feign.credential.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@Builder(builderClassName = "Builder")
public class CredentialCreateApi {

    @NotBlank
    String name;
    String description = "";
    Input inputs;

    @JsonProperty("credential_type")
    Integer credentialType;

    @Getter
    @lombok.Builder(builderClassName = "Builder")
    public static class Input {

        @JsonProperty("ssh_key_data")
        String privateKey;

        @JsonProperty("ssh_key_unlock")
        String passphrase;
    }
}
