package com.example.demo.user.entity.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(builderClassName = "Builder")
public class RecoveryToken {

    String id;
    String token;
    LocalDateTime sentDate;
    LocalDateTime expirationDate;
}
