package com.example.demo.awx.organization.aggregate.event;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxOrganizationCreatedEvent {

    UUID id;
    Long awxId;
    String name;
    String description;
}
