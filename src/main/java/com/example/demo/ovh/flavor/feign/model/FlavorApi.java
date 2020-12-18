package com.example.demo.ovh.flavor.feign.model;

import com.example.demo.ovh.feign.PlanCodeApi;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlavorApi {

    private String id;
    private String name;
    private boolean available;
    private Integer disk;
    private Integer inboundBandwidth;
    private String osType;
    private Integer outboundBandwidth;
    private PlanCodeApi planCodes;
    private Integer quota;
    private Integer ram;
    @JsonProperty("region")
    private String regionName;
    private String type;
    private Integer vcpus;
}
