package com.example.demo.user.aggregate.event;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class UserAuthSuccessEvent {

    UUID id;
    LocalDateTime lastLoginDate;
}
