package com.example.demo.ovh.feign.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class OvhInstanceGroupCreateApiRequest {

    String name;
    String region;
    String type;
}
