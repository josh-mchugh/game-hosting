package com.example.demo.web.verification.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ExistsUserByVerifyTokenQuery {

    String token;
}
