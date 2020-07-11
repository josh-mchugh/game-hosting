package com.example.demo.web.password.reset.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class PasswordResetRequest {

    String recoveryTokenId;
    String password;
}
