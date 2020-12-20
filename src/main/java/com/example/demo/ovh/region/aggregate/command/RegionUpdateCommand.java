package com.example.demo.ovh.region.aggregate.command;

import com.example.demo.ovh.region.entity.RegionStatus;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class RegionUpdateCommand {

    @NotNull
    @TargetAggregateIdentifier
    UUID id;
    String continentCode;
    String countryCodes;
    String dataCenterLocation;
    RegionStatus status;
}
