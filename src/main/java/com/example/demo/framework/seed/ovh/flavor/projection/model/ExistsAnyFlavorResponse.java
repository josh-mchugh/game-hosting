package com.example.demo.framework.seed.ovh.flavor.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@AllArgsConstructor
public class ExistsAnyFlavorResponse {

    @Accessors(fluent = true)
    boolean exists;
}
