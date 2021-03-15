package com.example.demo.ovh.region.scheduler.projection.model;

import com.example.demo.ovh.region.scheduler.projection.projection.RegionProjection;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchRegionByNameResponse {

    RegionProjection region;
}
