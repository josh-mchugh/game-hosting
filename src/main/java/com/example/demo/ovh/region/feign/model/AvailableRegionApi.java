package com.example.demo.ovh.region.feign.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AvailableRegionApi {

    private String name;
    private String continentCode;

    @JsonProperty("datacenterLocation")
    private String dataCenterLocation;
}
