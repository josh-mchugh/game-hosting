package com.example.demo.framework.seed.ovh.credential.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@AllArgsConstructor
public class ExistsAnyCredentialResponse {

    @Accessors(fluent = true)
    boolean exists;
}
