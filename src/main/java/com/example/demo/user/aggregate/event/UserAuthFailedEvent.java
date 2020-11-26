package com.example.demo.user.aggregate.event;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class UserAuthFailedEvent {

    UUID id;
}
