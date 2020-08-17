package com.example.demo.ovh.region.service.model;

import com.example.demo.ovh.region.entity.RegionStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class RegionUpdateRequest {

    String name;
    String continentCode;
    String countryCodes;
    String dataCenterLocation;
    RegionStatus status;
}
