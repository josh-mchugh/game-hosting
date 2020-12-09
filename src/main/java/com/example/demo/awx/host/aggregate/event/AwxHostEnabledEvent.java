package com.example.demo.awx.host.aggregate.event;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class AwxHostEnabledEvent {

    UUID id;
}
