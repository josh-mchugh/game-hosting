package com.example.demo.user.aggregate.event;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class UserPasswordChangedEvent {

    UUID id;
    String password;
}
