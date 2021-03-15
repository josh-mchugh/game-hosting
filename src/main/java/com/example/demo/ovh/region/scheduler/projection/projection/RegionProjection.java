package com.example.demo.ovh.region.scheduler.projection.projection;

import com.example.demo.ovh.region.entity.RegionStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

import java.util.UUID;

@Value
public class RegionProjection {

    UUID id;
    String continentCode;
    String countryCodes;
    String dataCenterLocation;
    RegionStatus status;

    @QueryProjection
    public RegionProjection(String id, String continentCode, String countryCodes, String dataCenterLocation, RegionStatus status) {
        this.id = UUID.fromString(id);
        this.continentCode = continentCode;
        this.countryCodes = countryCodes;
        this.dataCenterLocation = dataCenterLocation;
        this.status = status;
    }
}
