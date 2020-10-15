package com.example.demo.ovh.region.scheduler.service.model;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;

@Value
@Builder(builderClassName = "Builder")
public class ProcessRegionResponse {

    @Singular
    List<Object> updatedRegions;

    @Singular
    List<Object> createdRegions;
}
