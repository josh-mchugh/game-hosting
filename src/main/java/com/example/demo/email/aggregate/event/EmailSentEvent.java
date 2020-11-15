package com.example.demo.email.aggregate.event;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class EmailSentEvent {

    UUID id;
    LocalDateTime sentDate;
}
