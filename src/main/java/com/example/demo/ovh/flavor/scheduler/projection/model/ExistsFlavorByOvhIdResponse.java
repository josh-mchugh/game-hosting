package com.example.demo.ovh.flavor.scheduler.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@AllArgsConstructor
public class ExistsFlavorByOvhIdResponse {

    @Accessors(fluent = true)
    boolean exists;
}
