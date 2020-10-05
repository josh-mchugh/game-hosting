package com.example.demo.awx.host.aggregate.event;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
public class AwxHostCreatedEvent {

    private final UUID id;
    private final String awxInventoryId;
    private final String instanceId;
    private final Long hostId;
    private final String hostname;
    private final String description;
    private final Boolean enabled;
}
