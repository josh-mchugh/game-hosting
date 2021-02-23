package com.example.demo.web.password.forgot.command.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class FetchUserIdByEmailResponse {

    UUID id;
}
