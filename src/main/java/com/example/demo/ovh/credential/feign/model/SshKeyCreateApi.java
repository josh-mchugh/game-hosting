package com.example.demo.ovh.credential.feign.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class SshKeyCreateApi {

    String name;
    String publicKey;
    String region;
}
