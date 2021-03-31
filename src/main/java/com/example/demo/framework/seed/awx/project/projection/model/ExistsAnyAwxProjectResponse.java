package com.example.demo.framework.seed.awx.project.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@AllArgsConstructor
public class ExistsAnyAwxProjectResponse {

    @Accessors(fluent = true)
    boolean exists;
}
