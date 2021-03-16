package com.example.demo.framework.seed.ovh.region.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@AllArgsConstructor
public class ExistsAnyRegionResponse {

    @Accessors(fluent = true)
    boolean exists;
}
