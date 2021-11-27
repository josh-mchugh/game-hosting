package com.example.demo.web.verification.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class FetchUserIdByVerificationTokenResponse {

    UUID id;
}
