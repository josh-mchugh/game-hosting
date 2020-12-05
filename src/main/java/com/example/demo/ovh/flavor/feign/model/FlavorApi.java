package com.example.demo.ovh.flavor.feign.model;

import com.example.demo.ovh.feign.PlanCodeApi;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class FlavorApi {


    @JsonProperty("id")
    private String flavorId;
    private String name;
    private boolean available;
    private List<CapabilityApi> capabilities;
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
