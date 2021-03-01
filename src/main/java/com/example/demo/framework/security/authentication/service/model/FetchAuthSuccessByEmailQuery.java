package com.example.demo.framework.security.authentication.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchAuthSuccessByEmailQuery {

    String email;
}
