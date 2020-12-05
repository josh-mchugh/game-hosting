package com.example.demo.ovh.instance.feign.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class InstanceGroupCreateApi {

    String name;
    String region;
    String type;
}
