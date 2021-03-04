package com.example.demo.framework.security.session.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class FetchUserIdByEmailResponse {

    UUID id;
}
