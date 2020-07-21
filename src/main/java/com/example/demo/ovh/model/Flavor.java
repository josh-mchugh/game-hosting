package com.example.demo.ovh.model;

import lombok.Data;

import java.util.List;

@Data
public class Flavor {

    private String id;
    private String name;
    private boolean available;
    private List<Capability> capabilities;
    private Long disk;
    private Long inboundBandwidth;
    private String osType;
    private Long outboundBandwidth;
    private PlanCode planCodes;
    private Long quota;
    private Long ram;
    private String region;
    private String type;
    private Long vcpus;
}
