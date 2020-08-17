package com.example.demo.ovh.feign.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OvhAvailableRegionApiResponse {

    private String name;
    private String continentCode;

    @JsonProperty("datacenterLocation")
    private String dataCenterLocation;
}
