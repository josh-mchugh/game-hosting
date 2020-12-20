package com.example.demo.ovh.region.aggregate.event;

import com.example.demo.ovh.region.entity.RegionStatus;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class RegionUpdatedEvent {

    UUID id;
    String continentCode;
    String countryCodes;
    String dataCenterLocation;
    RegionStatus status;
}
