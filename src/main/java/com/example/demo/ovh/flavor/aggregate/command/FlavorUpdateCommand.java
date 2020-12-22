package com.example.demo.ovh.flavor.aggregate.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class FlavorUpdateCommand {

    @NotNull
    @TargetAggregateIdentifier
    UUID id;

    String name;
    String type;
    Boolean available;
    String hourly;
    String monthly;
    Integer quota;
    String osType;
    Integer vcpus;
    Integer ram;
    Integer disk;
    Integer inboundBandwidth;
    Integer outboundBandwidth;
}
