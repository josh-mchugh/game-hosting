package com.example.demo.user.aggregate.event;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UserWelcomeEmailEvent {

    String email;
}
