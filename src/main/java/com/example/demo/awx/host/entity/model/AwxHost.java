package com.example.demo.awx.host.entity.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxHost {

    UUID id;
    Long awxId;
    String hostname;
    String description;
    Boolean enabled;
}
