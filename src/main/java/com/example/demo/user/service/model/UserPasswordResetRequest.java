package com.example.demo.user.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class UserPasswordResetRequest {

    String password;
    String token;
}
