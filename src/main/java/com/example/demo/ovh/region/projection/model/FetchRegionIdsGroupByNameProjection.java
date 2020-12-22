package com.example.demo.ovh.region.projection.model;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchRegionIdsGroupByNameProjection {

    ImmutableMap<String, String> regionMap;
}
