package com.example.demo.ovh.region.feign.model;

import com.example.demo.ovh.region.entity.RegionStatus;
import lombok.Data;

import java.util.List;

@Data
public class RegionApi {

    private String name;
    private String continentCode;
    private String dataCenterLocation;
    private List<String> ipCountries;
    private RegionStatus status;
}
