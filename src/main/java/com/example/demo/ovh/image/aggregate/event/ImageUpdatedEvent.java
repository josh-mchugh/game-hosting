package com.example.demo.ovh.image.aggregate.event;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class ImageUpdatedEvent {

    UUID id;
    String ovhId;
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
