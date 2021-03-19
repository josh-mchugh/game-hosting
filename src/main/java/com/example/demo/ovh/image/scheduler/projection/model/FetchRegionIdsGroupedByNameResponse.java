package com.example.demo.ovh.image.scheduler.projection.model;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchRegionIdsGroupedByNameResponse {

    ImmutableMap<String, String> regions;
}
