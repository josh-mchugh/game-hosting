package com.example.demo.ovh.image.service.model;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(builderClassName = "Builder")
public class ImageUpdateRequest {

    String imageId;
    String regionName;
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
    ImmutableList<String> tags;
}
