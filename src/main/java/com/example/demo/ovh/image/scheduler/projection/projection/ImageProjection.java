package com.example.demo.ovh.image.scheduler.projection.projection;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class ImageProjection {

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

    @QueryProjection
    public ImageProjection(String id, String ovhId, String name, String type, LocalDateTime imageCreatedDate, String flavorType, String hourly, String monthly, Double size, Integer minRam, Integer minDisk, String username, String status, String visibility) {
        this.id = UUID.fromString(id);
        this.ovhId = ovhId;
        this.name = name;
        this.type = type;
        this.imageCreatedDate = imageCreatedDate;
        this.flavorType = flavorType;
        this.hourly = hourly;
        this.monthly = monthly;
        this.size = size;
        this.minRam = minRam;
        this.minDisk = minDisk;
        this.username = username;
        this.status = status;
        this.visibility = visibility;
    }
}
