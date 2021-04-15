package com.example.demo.framework.seed.awx.inventory.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@AllArgsConstructor
public class ExistsAnyAwxInventoryResponse {

    @Accessors(fluent = true)
    boolean exists;
}
