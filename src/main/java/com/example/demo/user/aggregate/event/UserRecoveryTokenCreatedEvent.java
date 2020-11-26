package com.example.demo.user.aggregate.event;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class UserRecoveryTokenCreatedEvent {

    UUID id;
    RecoveryToken recoveryToken;

    @Value
    @lombok.Builder(builderClassName = "Builder")
    public static class RecoveryToken {

        UUID id;
        String token;
        LocalDateTime sentDate;
        LocalDateTime expirationDate;
    }

    public static RecoveryToken createRecoveryToken(Long offset) {

        LocalDateTime sentDate = LocalDateTime.now();

        return RecoveryToken.builder()
                .id(UUID.randomUUID())
                .token(UUID.randomUUID().toString())
                .sentDate(sentDate)
                .expirationDate(sentDate.plus(offset, ChronoUnit.MILLIS))
                .build();
    }
}
