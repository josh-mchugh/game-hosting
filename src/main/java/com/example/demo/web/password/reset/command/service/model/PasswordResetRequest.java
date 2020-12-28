package com.example.demo.web.password.reset.command.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class PasswordResetRequest {

    String token;
    String password;
}
