package com.example.demo.web.registration.command.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class RegistrationCreateUserRequest {

    String email;
    String password;
}
