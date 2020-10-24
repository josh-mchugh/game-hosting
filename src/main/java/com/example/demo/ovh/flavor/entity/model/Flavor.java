package com.example.demo.ovh.flavor.entity.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class Flavor {

    String id;
    String flavorId;
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
