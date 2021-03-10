package com.example.demo.framework.seed.ovh.image.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@AllArgsConstructor
public class ExistsAnyImageResponse {

    @Accessors(fluent = true)
    boolean exists;
}
