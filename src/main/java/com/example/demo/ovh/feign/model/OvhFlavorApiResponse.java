package com.example.demo.ovh.feign.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OvhFlavorApiResponse {


    @JsonProperty("id")
    private String flavorId;
    private String name;
    private boolean available;
    private List<OvhCapabilityApi> capabilities;
    private Integer disk;
    private Integer inboundBandwidth;
    private String osType;
    private Integer outboundBandwidth;
    private OvhPlanCodeApi planCodes;
    private Integer quota;
    private Integer ram;
    @JsonProperty("region")
    private String regionName;
    private String type;
    private Integer vcpus;
}
