package com.example.demo.framework.seed.awx.organization.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@AllArgsConstructor
public class ExistsAnyAwxOrganizationResponse {

    @Accessors(fluent = true)
    boolean exists;
}
