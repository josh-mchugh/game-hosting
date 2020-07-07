package com.example.demo.web.registration.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class ValidatePasswordResponse {

    boolean valid;
    String errorMessage;
}
