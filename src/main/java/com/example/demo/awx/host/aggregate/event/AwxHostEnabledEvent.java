package com.example.demo.awx.host.aggregate.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class AwxHostEnabledEvent {

    private final UUID id;
}
