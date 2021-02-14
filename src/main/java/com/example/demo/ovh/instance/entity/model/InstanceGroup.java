package com.example.demo.ovh.instance.entity.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class InstanceGroup {

    UUID id;
    String ovhId;
    String name;
    String type;
}
