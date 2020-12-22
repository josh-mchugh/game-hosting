package com.example.demo.ovh.flavor.aggregate.event;

import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class FlavorUpdatedEvent {

    UUID id;
    String name;
    String type;
    Boolean available;
    String hourly;
    String monthly;
    Integer quota;
    String osType;
    Integer vcpus;
    Integer ram;
    Integer disk;
    Integer inboundBandwidth;
    Integer outboundBandwidth;
}
