package com.example.demo.util.password.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ValidatePasswordRequest {

    String password;
    String confirmPassword;
}
