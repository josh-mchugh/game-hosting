package com.example.demo.web.registration.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ExistsUserByEmailQuery {

    String email;
}
