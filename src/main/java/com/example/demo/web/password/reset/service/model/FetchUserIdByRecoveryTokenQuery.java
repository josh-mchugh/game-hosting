package com.example.demo.web.password.reset.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchUserIdByRecoveryTokenQuery {

    String token;
}
