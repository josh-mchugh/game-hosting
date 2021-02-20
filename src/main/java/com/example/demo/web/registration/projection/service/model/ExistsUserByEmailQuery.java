package com.example.demo.web.registration.projection.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ExistsUserByEmailQuery {

    String email;
}
