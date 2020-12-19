package com.example.demo.ovh.image.aggregate.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class ImageUpdateCommand {

    @NotNull
    @TargetAggregateIdentifier
    UUID id;

    @NotBlank
    String regionId;

    @NotBlank
    String ovhId;

    @NotBlank
    String name;

    String type;
    LocalDateTime imageCreatedDate;
    String flavorType;
    String hourly;
    String monthly;
    Double size;
    Integer minRam;
    Integer minDisk;
    String username;
    String status;
    String visibility;
}
