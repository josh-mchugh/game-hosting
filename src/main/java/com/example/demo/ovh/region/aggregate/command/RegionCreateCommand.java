package com.example.demo.ovh.region.aggregate.command;

import com.example.demo.ovh.region.entity.RegionStatus;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
public class RegionCreateCommand {

    @NotNull
    @TargetAggregateIdentifier
    private final UUID id;

    @NotBlank
    private final String name;

    private final String continentCode;
    private final String countryCodes;
    private final String dataCenterLocation;
    private final RegionStatus status;
}
