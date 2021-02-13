package com.example.demo.ovh.image.entity.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class Image {

    UUID id;
    String ovhId;
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
