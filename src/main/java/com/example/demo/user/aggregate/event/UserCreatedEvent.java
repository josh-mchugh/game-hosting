package com.example.demo.user.aggregate.event;

import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class UserCreatedEvent {

    UUID id;
    String email;
    String password;
    UserState state;
    UserType type;
    Verification verification;

    @Value
    @lombok.Builder(builderClassName = "Builder")
    public static class Verification {

        UUID id;
        String token;
        LocalDateTime sentDate;
    }

    public static Verification createVerification() {

        return Verification.builder()
                .id(UUID.randomUUID())
                .token(UUID.randomUUID().toString())
                .sentDate(LocalDateTime.now())
                .build();
    }
}
