package com.example.demo.framework.security.authentication.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class FetchAuthFailureByEmailResponse {

    UUID id;
}
