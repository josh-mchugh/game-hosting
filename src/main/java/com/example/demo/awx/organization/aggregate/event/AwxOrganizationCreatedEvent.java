package com.example.demo.awx.organization.aggregate.event;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
public class AwxOrganizationCreatedEvent {

    private final UUID id;
    private final Long organizationId;
    private final String name;
    private final String description;
}
