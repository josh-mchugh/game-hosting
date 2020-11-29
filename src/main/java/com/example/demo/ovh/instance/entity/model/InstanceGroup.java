package com.example.demo.ovh.instance.entity.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class InstanceGroup {

    String id;
    String groupId;
    String name;
    String type;
}