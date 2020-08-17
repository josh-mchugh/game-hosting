package com.example.demo.ovh.region.scheduler.service.model;

import com.example.demo.ovh.region.model.Region;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;

@Value
@Builder(builderClassName = "Builder")
public class ProcessRegionResponse {

    @Singular
    List<Region> updatedRegions;

    @Singular
    List<Region> createdRegions;
}
