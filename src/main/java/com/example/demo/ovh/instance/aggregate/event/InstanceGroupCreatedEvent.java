package com.example.demo.ovh.instance.aggregate.event;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class InstanceGroupCreatedEvent {

    UUID id;
    UUID projectId;
    String ovhId;
    String name;
    String type;
}
