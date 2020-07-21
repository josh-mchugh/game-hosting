package com.example.demo.ovh.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class SshKeyCreate {

    String name;
    String publicKey;
    String region;
}
