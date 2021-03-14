package com.example.demo.ovh.region.scheduler.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@AllArgsConstructor
public class ExistsRegionByNameResponse {

    @Accessors(fluent = true)
    boolean exists;
}
