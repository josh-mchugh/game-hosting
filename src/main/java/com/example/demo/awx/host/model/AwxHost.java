package com.example.demo.awx.host.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class AwxHost {

    String id;
    String hostname;
    String description;
}
