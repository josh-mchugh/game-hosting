package com.example.demo.ovh.feign.region.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AvailableRegionApi {

    private String name;
    private String continentCode;

    @JsonProperty("datacenterLocation")
    private String dataCenterLocation;
}
