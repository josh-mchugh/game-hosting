package com.example.demo.email.aggregate.event;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class EmailFailedEvent {

    UUID id;
}
