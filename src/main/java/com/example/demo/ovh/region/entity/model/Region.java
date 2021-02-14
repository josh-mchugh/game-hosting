package com.example.demo.ovh.region.entity.model;

import com.example.demo.ovh.region.entity.RegionStatus;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class Region {

    UUID id;
    String name;
    String continentCode;
    String countryCodes;
    String dataCenterLocation;
    RegionStatus status;
}
