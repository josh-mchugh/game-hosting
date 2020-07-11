package com.example.demo.web.password.forgot.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class ForgotPasswordResponse {

    boolean success;
    String emailAddress;
}
