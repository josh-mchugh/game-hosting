package com.example.demo.framework.security.user.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ExistsUserByEmailQuery {

    String email;
}
