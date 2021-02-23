package com.example.demo.web.password.forgot.command.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchUserIdByEmailQuery {

    String email;
}
