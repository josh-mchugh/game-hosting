package com.example.demo.web.registration.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
@AllArgsConstructor
public class ValidatePasswordRequest {

    String password;
    String confirmPassword;
}
