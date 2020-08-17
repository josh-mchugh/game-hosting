package com.example.demo.ovh.feign.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class OvhSshKeyCreateApiRequest {

    String name;
    String publicKey;
    String region;
}
