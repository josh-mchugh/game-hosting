package com.example.demo.user.entity.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class RecoveryToken {

    UUID id;
    String token;
    LocalDateTime sentDate;
    LocalDateTime expirationDate;
}
