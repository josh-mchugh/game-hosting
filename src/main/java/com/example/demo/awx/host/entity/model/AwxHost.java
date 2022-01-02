package com.example.demo.awx.host.entity.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class AwxHost {

    String id;
    Long awxId;
    String hostname;
    String description;
    Boolean enabled;
}
