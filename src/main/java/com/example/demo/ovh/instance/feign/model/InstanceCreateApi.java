package com.example.demo.ovh.instance.feign.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "builder")
public class InstanceCreateApi {

    String name;
    String flavorId;
    String region;
    String imageId;
    String groupId;
    String sshKeyId;
    Boolean monthlyBilling;
}
