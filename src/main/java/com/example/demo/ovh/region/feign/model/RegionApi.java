package com.example.demo.ovh.region.feign.model;

import com.example.demo.ovh.region.entity.RegionStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RegionApi {

    private String name;
    private String continentCode;
    @JsonProperty("datacenterLocation")
    private String dataCenterLocation;
    private List<String> ipCountries;
    private RegionStatus status;
}
