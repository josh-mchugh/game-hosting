package com.example.demo.framework.seed.ovh.flavor.projection.model;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchRegionIdsGroupedByNameResponse {

    ImmutableMap<String, String> regions;
}
