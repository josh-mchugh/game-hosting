package com.example.demo.ovh.region.aggregate.event;

import com.example.demo.ovh.region.entity.RegionStatus;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
public class RegionCreatedEvent {

    @TargetAggregateIdentifier
    private final UUID id;
    private final String name;
    private final String continentCode;
    private final String countryCodes;
    private final String dataCenterLocation;
    private final RegionStatus status;
}
