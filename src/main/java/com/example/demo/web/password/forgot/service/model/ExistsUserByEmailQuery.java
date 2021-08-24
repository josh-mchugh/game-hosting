package com.example.demo.web.password.forgot.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ExistsUserByEmailQuery {

    String email;
}
