package com.example.demo.web.password.reset.command.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class FetchUserIdByRecoveryTokenResponse {

    UUID id;
}
