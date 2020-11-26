package com.example.demo.user.aggregate.event;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class UserVerificationEmailEvent {

    String email;
    String token;
}
