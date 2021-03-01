package com.example.demo.framework.security.authentication.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@AllArgsConstructor
public class ExistsUserByEmailResponse {

    @Accessors(fluent = true)
    boolean exists;
}
