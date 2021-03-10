package com.example.demo.ovh.flavor.scheduler.projection.projection;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

import java.util.UUID;

@Value
public class FlavorProjection {

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

    @QueryProjection
    public FlavorProjection(String id, String name, String type, Boolean available, String hourly, String monthly, Integer quota, String osType, Integer vcpus, Integer ram, Integer disk, Integer inboundBandwidth, Integer outboundBandwidth) {
        this.id = UUID.fromString(id);
        this.name = name;
        this.type = type;
        this.available = available;
        this.hourly = hourly;
        this.monthly = monthly;
        this.quota = quota;
        this.osType = osType;
        this.vcpus = vcpus;
        this.ram = ram;
        this.disk = disk;
        this.inboundBandwidth = inboundBandwidth;
        this.outboundBandwidth = outboundBandwidth;
    }
}
