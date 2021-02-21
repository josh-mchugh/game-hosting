package com.example.demo.web.password.reset.projection.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@AllArgsConstructor
public class ExistsByRecoveryTokenResponse {

    @Accessors(fluent = true)
    boolean exists;
}
