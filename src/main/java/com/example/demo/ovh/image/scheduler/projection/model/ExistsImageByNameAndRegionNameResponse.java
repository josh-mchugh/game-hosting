package com.example.demo.ovh.image.scheduler.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@AllArgsConstructor
public class ExistsImageByNameAndRegionNameResponse {

    @Accessors(fluent = true)
    boolean exists;
}
