package com.example.demo.web.password.forgot.command.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@AllArgsConstructor
public class ExistsUserByEmailResponse {

    @Accessors(fluent = true)
    boolean exists;
}
