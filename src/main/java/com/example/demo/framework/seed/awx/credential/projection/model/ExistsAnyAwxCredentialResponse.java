package com.example.demo.framework.seed.awx.credential.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@AllArgsConstructor
public class ExistsAnyAwxCredentialResponse {

    @Accessors(fluent = true)
    boolean exists;
}
