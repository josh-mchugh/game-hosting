package com.example.demo.util.password.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class ValidatePasswordResponse {

    boolean valid;
    String errorMessageKey;
    String errorDefaultMessage;
}
