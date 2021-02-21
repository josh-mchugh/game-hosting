package com.example.demo.web.password.reset.projection.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ExistsByRecoveryTokenQuery {

    String token;
}
