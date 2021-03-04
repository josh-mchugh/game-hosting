package com.example.demo.framework.security.session.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchUserIdByEmailQuery {

    String email;
}
